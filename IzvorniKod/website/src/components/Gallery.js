import React, { useState, useEffect, useMemo, useCallback } from 'react';
import '../styles/Gallery.css';

const ImageGallery = () => {
  const images = useMemo(() => [
    'https://ic.recreationid.com/outdoor-recreation/pages/camp-kitchen/camp-kitchen_big_0.jpg',
    'https://www.electroluxprofessional.com/us/wp-content/uploads/2023/07/ESL-Blog1-Featured-Image-Banner.jpg',
    'https://house.ergonfoods.com/wp-content/uploads/2019/07/cookingbg02.jpg',
    'https://www.brc.com.au/Images/UserUploadedImages/2746/0922_BRC_Food-0669-resized-canva-1920x550-oysters.png'
  ], []);

  const [currentImageIndex, setCurrentImageIndex] = useState(0);

  const changeImages = useCallback(() => {
    setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
  }, [images]);

  useEffect(() => {
    const intervalId = setInterval(changeImages, 5000);

    return () => clearInterval(intervalId);
  }, [currentImageIndex, changeImages]);

  const handlePrev = () => {
    setCurrentImageIndex((prevIndex) => (prevIndex - 1 + images.length) % images.length);
  };

  const handleNext = () => {
    setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
  };

  return (
    <div className="image-gallery-container">
      <button className="gallery-button" onClick={handlePrev}>&lt;</button>
      <img className="gallery-image" src={images[currentImageIndex]} alt={`${currentImageIndex + 1}`} />
      <button className="gallery-button" onClick={handleNext}>&gt;</button>
    </div>
  );
};

export default ImageGallery;
