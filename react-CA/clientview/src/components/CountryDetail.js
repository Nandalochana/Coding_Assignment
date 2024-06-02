import React, { useEffect, useState } from 'react';
import axios from 'axios';

/**
 * This part will handle the specific sepected country information  
 * @returns  CountryDetail
 */
const CountryDetail = ({ countryName }) => {
    const [country, setCountry] = useState(null);

    useEffect(() => {
        if (countryName) {
            axios.get(`http://localhost:8080/countries/${countryName}`)
                .then(response => {
                    setCountry(response.data);
                })
                .catch(error => {
                    console.error('There was an error fetching the country details!', error);
                });
        }
    }, [countryName]);

    return (
        <div>
            {country ? (
                <div>
                    <h2>{country.name}</h2>
                    <p>Country Code: {country.countryCode}</p>
                    <p>Capital: {country.capital}</p>
                    <p>Population: {country.population}</p>
                    <img src={`${country.flagFileUrl}`} alt={`${country.name} flag`} width="100" />
                </div>
            ) : (
                <p>After Load Please Select one to see more details</p>
            )}
        </div>
    );
};

export default CountryDetail;
