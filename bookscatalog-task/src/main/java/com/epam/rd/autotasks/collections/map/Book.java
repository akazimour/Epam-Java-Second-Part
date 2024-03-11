package com.epam.rd.autotasks.collections.map;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class Book implements Comparable<Book> {
    private final String title;
    private final List<String> genres;
    private BigDecimal cost;

    public Book(String title, List<String> genres, BigDecimal cost) {
        this.title = title;
        this.genres = genres;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(genres, book.genres) && Objects.equals(cost, book.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genres, cost);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", genres=" + genres +
                ", cost=" + (cost == null ? "unavailable" : cost) +
                '}';
    }

    /**
     * The natural ordering is by title in ascending order,
     * then by cost in ascending order with {@code null}s at the end.
     *
     * @param   o the object to be compared.
     * @return  a negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
     *
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException if the specified object's type prevents it
     *         from being compared to this object.
     */
    @Override
    public int compareTo(Book o) {
        if (o==null){
            throw new NullPointerException();
        }
        int i = this.title.compareTo(o.getTitle());
        int compare = Integer.compare(i, 0);

        if (compare == 0){

            if(o.getCost().compareTo(BigDecimal.ZERO)<0){
                return 0;
            };
            if (this.cost == null || this.cost.compareTo(BigDecimal.ZERO)<0){
                this.cost = BigDecimal.ZERO;
            }
            compare = this.cost.compareTo(o.getCost());
            if (this.cost.equals(BigDecimal.ZERO)){
                return 1;
            }
            if (o.getCost().compareTo(this.cost)<0){
                return 1;

            }
        }


        return compare;
    }
    }

