import React, { useState, useEffect, useMemo, useCallback } from 'react';
import '../styles/Gallery.css';

const ImageGallery = () => {
  const images = useMemo(() => [
    'https://www.sterlingvineyards.com/dw/image/v2/BDBC_PRD/on/demandware.static/-/Sites-Sterling-Library/default/dwcb2903a9/images/@Banner_Images/1920x550_(Half_Height)/1920%20X%20550%20STV%20Brunch%20Banner.png',
    'https://www.thebigchefuddingston.co.uk/image/cache/data/homeslider01-1920x550.jpg',
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
