import React, { useState } from 'react';
import CountryList from './components/CountryList';
import CountryDetail from './components/CountryDetail';
import './App.css';

/**
 * This part will handle the main page of the application 
 * @returns : The main page information
 */
const App = () => {
    const [selectedCountry, setSelectedCountry] = useState('');

    return (
        <div className="App">
            <CountryDetail countryName={selectedCountry} />
            <CountryList onSelectCountry={setSelectedCountry} />
           
        </div>
    );
};

export default App;
