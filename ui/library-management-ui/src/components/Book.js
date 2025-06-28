import React, { useEffect, useState } from 'react'

const Book = (() => {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState([]);
  const [error, setError] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/books/getAllBooks").then((response) => {

      return response.json()
      // if (!response.message && !response.error)
      // {
      //     return response.json();
      // }
      // else 
      // {
      //     throw new Error("Failed to fetch books!");
      // }
    }).then(
        (data) => {
            setBooks(data);
            setLoading(false);
        }
    )
    .catch((err) => {
      setError(err.message);
      setLoading(false);
    })
  }, []);

  // if (loading) return <p> Loading books!</p>
  // if (error) return <p> Error: {error}</p>
  return (
    <div>
      <h2>Books List</h2>
      <ul>
        {books.map((book, index) => (
          <li key={index}>
            {book.title} by {book.author}
          </li>
        ))}
      </ul>
    </div>
  );

});

export default Book;
