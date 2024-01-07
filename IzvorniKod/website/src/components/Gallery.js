import React, { useState, useEffect } from 'react';

const ImageGallery = () => {
  const images =
      ['https://media.istockphoto.com/id/1391598401/photo/students-in-cookery-class-mixing-ingredients-for-recipe-in-kitchen-group-of-young-people.jpg?s=612x612&w=0&k=20&c=ooqwYaeGusn3c-9ISRYge6lTqoRtimXQgiwm2SeURVQ='
      ,'https://t4.ftcdn.net/jpg/03/32/75/39/360_F_332753934_tBacXEgxnVplFBRyKbCif49jh0Wz89ns.jpg'
      ,'https://domf5oio6qrcr.cloudfront.net/medialibrary/11537/4a78f148-d427-4209-8173-f33d04c44106.jpg'];

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
        <img
            src={images[currentImageIndex]}
            alt={`${currentImageIndex + 1}`}
            style={{ height: '400px', objectFit: 'cover' }}
        />
        <div>
          <button onClick={handlePrev}>Previous</button>
          <button onClick={handleNext}>Next</button>
        </div>
      </div>
  );
};

export default ImageGallery;
