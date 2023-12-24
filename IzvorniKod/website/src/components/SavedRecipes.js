import React, { useState, useEffect } from 'react';
import RecipeCard from './RecipeCard';
import '../styles/Saved-RecepieList.css';
import { useNavigate, useLocation } from 'react-router-dom';
import Paging from './Paging';

const itemsPerPage = 4;

function SavedRecipes() {
    //trebalo bi implementirati tako da se preko recipelist dohvate rezultati iz baze, pa nakon toga ovo sto slijedi
    //za sada sam samo isprobavao ovu funkcionalnost sa savedRecipes varijablom
    const savedRecipes = [
        {
            name: 'Mjauuuuu',
            description: 'Varivo od graha',
            imageSrc: 'https://live.staticflickr.com/6209/6063498585_33b78332cc.jpg',
        },
        {
            name: 'Palenta',
            description: 'Ovo je recept za palentu',
            imageSrc: 'https://1.bp.blogspot.com/_AWEhM2Nug4g/SV2M1PypdDI/AAAAAAAAC3s/fIKgKOVVgo4/s400/Polenta+4.jpg',
        },
        {
            name: 'Pizza',
            description: 'Recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu',
            imageSrc: 'https://c2.staticflickr.com/4/3374/3572925000_693b458fcb_b.jpg',
        },
        {
            name: 'Ante',
            description: 'Recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu',
            imageSrc: 'https://c2.staticflickr.com/4/3374/3572925000_693b458fcb_b.jpg',
        },
        {
            name: 'Nina',
            description: 'Recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu',
            imageSrc: 'https://c2.staticflickr.com/4/3374/3572925000_693b458fcb_b.jpg',
        },
        {
            name: 'D',
            description: 'Recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu',
            imageSrc: 'https://c2.staticflickr.com/4/3374/3572925000_693b458fcb_b.jpg',
        },{
            name: 'TATAT',
            description: 'Varivo od graha',
            imageSrc: 'https://live.staticflickr.com/6209/6063498585_33b78332cc.jpg',
        },
        {
            name: 'MJAu',
            description: 'Ovo je recept za palentu',
            imageSrc: 'https://1.bp.blogspot.com/_AWEhM2Nug4g/SV2M1PypdDI/AAAAAAAAC3s/fIKgKOVVgo4/s400/Polenta+4.jpg',
        },{
            name: 'KRUH',
            description: 'Varivo od graha',
            imageSrc: 'https://live.staticflickr.com/6209/6063498585_33b78332cc.jpg',
        },
        {
            name: 'PAS',
            description: 'Ovo je recept za palentu',
            imageSrc: 'https://1.bp.blogspot.com/_AWEhM2Nug4g/SV2M1PypdDI/AAAAAAAAC3s/fIKgKOVVgo4/s400/Polenta+4.jpg',
        },
        {
            name: 'KRava',
            description: 'Ovo je recept za palentu',
            imageSrc: 'https://1.bp.blogspot.com/_AWEhM2Nug4g/SV2M1PypdDI/AAAAAAAAC3s/fIKgKOVVgo4/s400/Polenta+4.jpg',
        },
        {
            name: 'dž',
            description: 'Ovo je recept za palentu',
            imageSrc: 'https://1.bp.blogspot.com/_AWEhM2Nug4g/SV2M1PypdDI/AAAAAAAAC3s/fIKgKOVVgo4/s400/Polenta+4.jpg',
        },
        {
            name: 'ijauuu',
            description: 'Ovo je recept za palentu',
            imageSrc: 'https://1.bp.blogspot.com/_AWEhM2Nug4g/SV2M1PypdDI/AAAAAAAAC3s/fIKgKOVVgo4/s400/Polenta+4.jpg',
        },
    ];

  
    const navigate = useNavigate();
    const location = useLocation();
    const [currentPage, setCurrentPage] = useState(1);

    useEffect(() => {
        const params = new URLSearchParams(location.search);
        const page = parseInt(params.get('page')) || 1;
        setCurrentPage(page);
    }, [location.search]);

    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const recipesToDisplay = savedRecipes.slice(startIndex, endIndex);

    const handlePageChange = (page) => {
        setCurrentPage(page);
        navigate(`?page=${page}`);
    };

    return (
        <> 
                <div className="recipe-cards-container">
                    {recipesToDisplay.map(r => (
                        <RecipeCard key={r.name} recipeName={r.name} description={r.description} imageSrc={r.imageSrc} />
                    ))}
                </div>
            <div className='paging-container'>
                <Paging
                    totalItems={savedRecipes.length}
                    itemsPerPage={itemsPerPage}
                    currentPage={currentPage}
                    onPageChange={handlePageChange}
                />
            </div>
        </>
    );
}


export default SavedRecipes;
