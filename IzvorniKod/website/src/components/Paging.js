import React from 'react';
import '../styles/Paging.css';

function Paging({ totalItems, itemsPerPage, currentPage, onPageChange }) {
    const totalPages = Math.ceil(totalItems / itemsPerPage);

    const handleFirstClick = () => {
        onPageChange(1);
    };

    const handleLastClick = () => {
        onPageChange(totalPages);
    };

    const handlePrevClick = () => {
        const prevPage = currentPage > 1 ? currentPage - 1 : 1;
        onPageChange(prevPage);
    };

    const handleNextClick = () => {
        const nextPage = currentPage < totalPages ? currentPage + 1 : totalPages;
        onPageChange(nextPage);
    };

    return (
        <div className="paging">
            {currentPage >= 2 && (
                <button onClick={handleFirstClick}>
                    1
                </button>
            )}
            {currentPage > 2 && (
                <button onClick={handlePrevClick}>
                    &lt;
                </button>
            )}
            <button disabled>
                {currentPage}
            </button>
            {currentPage < totalPages - 1 && (
                <button onClick={handleNextClick}>
                    &gt;
                </button>
            )}
            {currentPage < totalPages && (
                <button onClick={handleLastClick}>
                    {totalPages}
                </button>
            )}
        </div>
    );
}

export default Paging;
