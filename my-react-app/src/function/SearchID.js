import React, { useState, useEffect } from "react";

const SearchID = ({ eventDataUrl }) => {
    const [eventData, setEventData] = useState([]);
    const [searchID, setSearchID] = useState('');
    const [searchResult, setSearchResult] = useState(null);

    const handleSearch = async () => {
        try {
            const response = await fetch(eventDataUrl);
            const data = await response.json();

            const foundData = data.filter(event => event.eventID === searchID);

            setEventData(data);
            setSearchResult(foundData.length > 0 ? foundData : null);
        } catch (error) {
            console.error("Error fetching event data:", error);
        }
    };

    useEffect(() => {
        if (searchID !== '') {
            handleSearch();
        } else {
            setEventData([]);
            setSearchResult(null);
        }
    }, [searchID]);

    return (
        <div>
            <label>
                Search by Event ID:
                <input
                    type="text"
                    value={searchID}
                    onChange={(e) => setSearchID(e.target.value)}
                />
            </label>
            <button onClick={handleSearch}>Search</button>

            {searchResult !== null && (
                <div>
                    {searchResult.length > 0 ? (
                        <div>
                            <h3>Search Results:</h3>
                            <ul>
                                {searchResult.map((event, index) => (
                                    <li key={index}>{JSON.stringify(event)}</li>
                                ))}
                            </ul>
                        </div>
                    ) : (
                        <p>No data fsound for the given Event ID.</p>
                    )}
                </div>
            )}
        </div>
    );
};

export default SearchID;
