import React from 'react';
import '../styles/Paging.css';

function Paging({ totalItems, itemsPerPage, currentPage, onPageChange}) {
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
            {currentPage > 2 && (
                <button onClick={handleFirstClick}>
                    &lt;&lt;
                </button>
            )}
            {currentPage >= 2 && (
                <button onClick={handlePrevClick}>
                    {currentPage - 1}
                </button>
            )}
            <button disabled>
                {currentPage}
            </button>
            {currentPage < totalPages && (
                <button onClick={handleNextClick}>
                    {currentPage + 1}
                </button>
            )}
            {currentPage < totalPages - 1 && (
                <button onClick={handleLastClick}>
                    &gt;&gt;
                </button>
            )}
        </div>
    );
}

export default Paging;
