import React, { useState } from 'react';
import axios from 'axios';
import './css/CountryList.css'; // Import the CSS file

/**
 * This part will return the all county information 
 * @returns  CountryList
 */
const CountryList = ({ onSelectCountry }) => {
    const [countries, setCountries] = useState([]);
    const [loaded, setLoaded] = useState(false);

    const loadCountries = () => {
        axios.get('http://localhost:8080/countries/')
            .then(response => {
                setCountries(response.data);
                setLoaded(true);
            })
            .catch(error => {
                console.error('There was an error fetching the countries!', error);
            });
    };

    return (
        <div>
            <h1>Country List </h1>
            <button onClick={loadCountries}>Click for load Countries</button>
            {loaded ? (
                <div className="country-list">
                    {countries.map((country) => (
                        <div key={country.countryCode}>
                            <a href="#" onClick={() => onSelectCountry(country.name)}>
                               {country.name}:({country.countryCode})
                            </a>
                        </div>
                    ))}
                </div>
            ) : (
                <p>Click the button to load countries.</p>
            )}
        </div>
    );
};

export default CountryList;