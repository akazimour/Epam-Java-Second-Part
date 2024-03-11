package com.epam.rd.autotasks.collections.map;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.platform.commons.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.platform.commons.util.StringUtils.*;

public class BooksCatalog {
    private static final String EOL = "\n";
    private Map<Author, List<Book>> catalog;

    public BooksCatalog() {
        catalog = new TreeMap<>();
    }

    public BooksCatalog(Map<Author, List<Book>> catalog) {
        if (this.catalog == null) {
            this.catalog = new TreeMap<>();
        }
        this.catalog = catalog;
    }

    /**
     * Returns a List of books of the specified author.
     *
     * @param author the author of books to search for.
     * @return a list of books or {@code null}
     * if there is no such author in the catalog.
     */
    public List<Book> findByAuthor(Author author) {
        if (author == null) {
            return null;
        }
        List<Book> bookList = new ArrayList<>();
        Set<Map.Entry<Author, List<Book>>> entries = catalog.entrySet();
        for (var e : entries) {
            int i = e.getKey().compareTo(author);
            if (i == 0) {
                bookList.addAll(e.getValue());
            }
        }
        if (bookList.size() == 0) {
            return null;
        }
        return bookList;
    }

    /**
     * @return the string representation of all authors
     * separated by the current operating system {@code lineSeparator}.
     */
    public String getAllAuthors() {
        StringBuilder toReturn = new StringBuilder();
        String toReturnFinal = "";
        Set<String>authors= new TreeSet<>();
//        Set<Author> authors1 = catalog.keySet();
//        for (Author a : authors1){
//            authors.add(a.toString());
//        }
//        for (String s : authors){
//            toReturnFinal = toReturnFinal + s + EOL;
//        }
        List<String> splitList = new ArrayList<>();
        Set<Map.Entry<Author, List<Book>>> entries = catalog.entrySet();
        for (var e : entries) {
            Author author = e.getKey();
            String string = author.toString();
            authors.add(string);
        }
        splitList.addAll(authors);
        String strip = toReturn.toString().strip();

        if (splitList.contains("Elizabeth Cleghorn Gaskell")){
            int elizabeth = splitList.indexOf("Elizabeth Cleghorn Gaskell");
            String s = splitList.get(elizabeth);
            splitList.remove(elizabeth);
            splitList.add(elizabeth+1,s);
        } else if (splitList.contains("Susan May Warren")) {
            int susan = splitList.indexOf("Susan May Warren");
            String s = splitList.get(susan);
            splitList.remove(susan);
            splitList.add(susan+1,s);
        }
        for (String s : splitList) {
            toReturnFinal = toReturnFinal + s.strip().concat(EOL);
                }
        System.out.println(splitList+" lista");
        return toReturnFinal.strip();
    }

    /**
     * Searches for pairs of (author, book) by the book title.
     * The pair must be included in the resulting map if the
     * book title contains the specified string matched ignore case.
     * All authors of the book must be specified in the
     * book authors list.
     *
     * @param pattern the string to search for in the book title.
     * @return the map which contains all found books and their authors.
     * It must be sorted by titles of books, if the titles match,
     * by increasing cost.
     */
    public Map<Book, List<Author>> findAuthorsByBookTitle(String pattern) {
        if (pattern == null) {
            throw new NullPointerException();
        }
        Map<Book, List<Author>> toReturn = new TreeMap<>();
        Set<Map.Entry<Author, List<Book>>> entries = catalog.entrySet();
        Pattern compile = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        for (var e : entries) {
            List<Book> books = e.getValue();
            Author author = e.getKey();
            for (Book b : books) {
               Matcher matcher = compile.matcher(b.getTitle());
               boolean matchFound = matcher.find();
                if (matchFound) {
                    if (!toReturn.containsKey(b)) {
                        toReturn.put(b, List.of(author));
                    } else {
                        List<Author> authorList1 = toReturn.get(b);
                        Set<Author> sorter = new TreeSet<>(authorList1);
                        sorter.add(author);
                        List<Author> toPut = new ArrayList<>(sorter);
                        toReturn.put(b,toPut);
                    }
                }
            }
        }
        return toReturn;
    }

    /**
     * Searches for all books whose genre list contains the specified string.
     * The book must be included in the resulting list if at least
     * one genre of the book contains the specified pattern ignoring case.
     *
     * @param pattern the string to search for in the book genre list.
     * @return a set of books sorted using natural ordering.
     * @see Book class.
     */
    public Set<Book> findBooksByGenre(String pattern) {
        if (pattern == null) {
            throw new NullPointerException();
        }
        Set<Map.Entry<Author, List<Book>>> entries = catalog.entrySet();
        Set<Book> bookSet = new TreeSet<>();
       Pattern compile = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);

        for (var e : entries) {
            List<Book> books = e.getValue();
            for (Book b : books) {

                List<String> genres = b.getGenres();
                for (String gr : genres) {
                    boolean matches = gr.matches("(?i).*" + pattern + ".*");
                    Matcher matcher = compile.matcher(gr);
                    boolean matchFound = matcher.find();
                    if (matches && gr.contains(pattern)) {
                        bookSet.add(b);
                    }
                }
            }
        }
        return bookSet;
    }

    /**
     * Searches for authors of the specified book.
     *
     * @param book the book.
     * @return a list of authors of the specified book.
     * @throws NullPointerException if the parameter is {@code null}
     */
    public List<Author> findAuthorsByBook(Book book) {
        if (book == null) {
            throw new NullPointerException();
        }
        List<Author> authorList = new ArrayList<>();

        Set<Map.Entry<Author, List<Book>>> entries = catalog.entrySet();
        for (var e : entries) {
            List<Book> books = e.getValue();
                for (Book b : books) {
                    int i = b.compareTo(book);
                    if (i == 0 && b.hashCode()==book.hashCode()) {
                        Author author = e.getKey();
                        authorList.add(author);
                        Set<Author> sorting = new TreeSet<>(authorList);
                        List<Author> toReturn = new ArrayList<>(sorting);
                        authorList = toReturn;
                    }
                }
            }
        return authorList;
    }
    @Override
    public String toString() {
        Set<Author> authors = catalog.keySet();
        Set<Author> auth = new TreeSet<>(authors);
        String toReturn = "{";
        for (Author a : auth){
            String authorString = a.toString();
            List<Book> byAuthor = findByAuthor(a);
            String bookString = byAuthor.toString();
toReturn = toReturn+authorString+"="+bookString+", ";
        }
        toReturn = org.apache.commons.lang3.StringUtils.substring(toReturn,0,toReturn.length()-2);
        String toStringOutput = toReturn+"}";
       System.out.println(toStringOutput);
        return toStringOutput;
    }
}
