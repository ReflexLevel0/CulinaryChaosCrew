import React, { useState, useEffect } from 'react';

const ImageGallery = () => {
  const images = ['https://media.istockphoto.com/id/1391598401/photo/students-in-cookery-class-mixing-ingredients-for-recipe-in-kitchen-group-of-young-people.jpg?s=612x612&w=0&k=20&c=ooqwYaeGusn3c-9ISRYge6lTqoRtimXQgiwm2SeURVQ=', 'https://plus.unsplash.com/premium_photo-1683707120106-a32a3c9942ef?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTd8fGN1bGluYXJ5fGVufDB8fDB8fHww'];

  const [currentImageIndex, setCurrentImageIndex] = useState(0);

  const changeImages = () => {
    setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
  };

  useEffect(() => {
    const intervalId = setInterval(changeImages, 5000);

    return () => clearInterval(intervalId);
  }, [currentImageIndex, images]); 

  const handlePrev = () => {
    setCurrentImageIndex((prevIndex) => (prevIndex - 1 + images.length) % images.length);
  };

  const handleNext = () => {
    setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
  };

  return (
    <div>
      <img src={images[currentImageIndex]} alt={`${currentImageIndex + 1}`} />
      <div>
        <button onClick={handlePrev}>Previous</button>
        <button onClick={handleNext}>Next</button>
      </div>
    </div>
  );
};

export default ImageGallery;