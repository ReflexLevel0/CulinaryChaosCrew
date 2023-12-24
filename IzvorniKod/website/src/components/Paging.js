import React from 'react';
import '../styles/Paging.css';

function Paging({totalItems, itemsPerPage, currentPage, onPageChange }) {
  const totalPages = Math.ceil(totalItems / itemsPerPage);

  const handlePageChange = (page) => {
    if (page >= 1 && page <= totalPages && page !== currentPage) {
      onPageChange(page);
    }
  };

  // Generate an array of page numbers
  const pages = Array.from({ length: totalPages }, (_, index) => index + 1);

  return (
    <div className='pagination-container'>
      <ul className="pagination">
        {pages.map((page) => (
          <li
            key={page}
            className={page === currentPage ? 'active' : ''}
            onClick={() => handlePageChange(page)}
          >
            {page}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Paging;
