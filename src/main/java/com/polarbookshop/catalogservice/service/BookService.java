package com.polarbookshop.catalogservice.service;

import org.springframework.stereotype.Service;

import com.polarbookshop.catalogservice.entity.Book;
import com.polarbookshop.catalogservice.exception.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.exception.BookNotFoundException;
import com.polarbookshop.catalogservice.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Iterable<Book> viewBookList() {
		return bookRepository.findAll();
	}

	public Book viewBookDetails(String isbn) {
		return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
	}

	public Book addBookToCatalog(Book book) {
		if (bookRepository.existsByIsbn(book.getIsbn())) {
			throw new BookAlreadyExistsException(book.getIsbn());
		}
		return bookRepository.save(book);
	}

	public void removeBookFromCatalog(String isbn) {
		bookRepository.deleteByIsbn(isbn);
	}

	public Book editBookDetails(String isbn, Book book) {
		return bookRepository.findByIsbn(isbn).map(existingBook -> {
			var bookToUpdate = new Book(existingBook.getIsbn(), book.getTitle(), book.getAuthor(), book.getPrice());
			return bookRepository.save(bookToUpdate);
		}).orElseGet(() -> addBookToCatalog(book));
	}

}
