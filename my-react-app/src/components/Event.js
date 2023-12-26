import React, { useEffect, useState } from 'react';
import DeleteForm from "./DeleteForm";
import EditEvent from "../function/EditEvent";
import axios from 'axios';

//Event ( eventID : int, eventName : varchar, eventDate : date, eventTime : time, venueID : varchar )

const Event = () => {
    const [popupVisible, setPopupVisible] = useState(false);
    const [formData, setFormData] = useState({
        eventName: '',
        eventID: '',
        eventDate: '',
        eventTime: '',
        venueID: ''
    })

    const [searchID, setSearchID] = useState('');
    const [searchedData, setSearchedData] = useState(null);
    const [dataPopupVisible, setDataPopupVisible] = useState(false);
    const [editedData, setEditedData] = useState('');



    const [allEvents, setAllEvents] = useState([]); // new state for storing all events
    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch('https://mocki.io/v1/8de749a8-aaff-4226-ad8c-7d9bafa586a1');
                if (!response.ok) {
                    throw new Error(`Failed to fetch data. Status: ${response.status}`);
                }

                const data = await response.json();
                setAllEvents(data);
            } catch (error) {
                console.error('Error fetching data:', error.message);
            }
        };

        fetchData();
    }, []);




    const handleButtonClick = () => {
        setPopupVisible(!popupVisible);
    };
    const closePopup = () => {
        setPopupVisible(false);
        setDataPopupVisible(false);
    };

    const handleSaveClick = () => {
        console.log('Saving data:', editedData);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSearch = () => {
        console.log('Searching data for ID:', searchID);
        const foundData = fetchDataById(searchID);
        setSearchedData(foundData);
        setDataPopupVisible(!!foundData);

        setDataPopupVisible(true);
    };

    const fetchDataById = (id) => {
        const foundEvent = allEvents.find((event) => event.eventID === searchID);

        if (foundEvent) {
            return foundEvent;
        } else {
            console.error(`Event with ID ${id} not found`);
            return null;
        }
    };



    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (!formData.eventName || !formData.eventDate || !formData.eventTime || !formData.venueID) {
                console.error('Please fill in all fields');
                return;
            }

            const existingDataResponse = await axios.get('/scratch.json');
            const existingData = existingDataResponse.data || [];

            const newEventID = Math.floor(Math.random() * 1000);

            const newEvent = {
                eventName: formData.eventName,
                eventDate: formData.eventDate,
                eventTime: formData.eventTime,
                venueID: formData.venueID,
                eventID: newEventID,
            };

            const updatedData = [...existingData, newEvent];

            await axios.post('/scratch.json', updatedData);

            console.log('Data submitted successfully');
            setFormData({
                eventName: '',
                eventDate: '',
                eventTime: '',
                venueID: '',
            });
        } catch (error) {
            console.error('Error submitting data:', error.message);
        }
    };




    const handleReset = () => {
        setFormData({
            eventName: '',
            eventDate: '',
            eventTime: '',
            venueID: ''
        });
    };


    return (
        <div>
            <div>
                <button
                    type="button"
                    className="btn btn-secondary custom-sub-button"
                    onClick={handleButtonClick} >
                    Update Event
                </button>
                {popupVisible && (
                    <div className="popup">
                        <div className="popup-content">
                            <form className="lg:w-[50%] w-[35%]" onSubmit={handleSubmit}>
                                <label className="form-title">Insert New Data :</label><br />

                                <label className="labelStyle">
                                    Event Name:
                                    <input
                                        type="text"
                                        name="eventName"
                                        value={formData.eventName}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Event Date:
                                    <input
                                        type="text"
                                        name="eventDate"
                                        value={formData.eventDate}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Event Time:
                                    <input
                                        type="text"
                                        name="eventTime"
                                        value={formData.eventTime}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Venue ID:
                                    <input
                                        type="number"
                                        name="venueID"
                                        value={formData.venueID}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Event ID:
                                    <input
                                        type="number"
                                        name="eventID"
                                        value={formData.eventID}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <div>
                                    <button
                                        type="reset"
                                        onClick={handleReset}
                                        className="btn btn-secondary custom-sub-button"
                                    >
                                        Clear
                                    </button>
                                    <button
                                        type="submit"
                                        onClick={handleSubmit}
                                        className="btn btn-secondary custom-sub-button"
                                    >
                                        Submit
                                    </button>
                                    <button onClick={closePopup} className="btn btn-secondary custom-sub-button">
                                        Close
                                    </button>
                                </div>
                            </form>


                            <div>
                                <DeleteForm />
                                <label className="form-title">
                                    Search Event by ID:
                                    <input
                                        type="number"
                                        name="searchID"
                                        value={searchID}
                                        onChange={(e) => setSearchID(e.target.value)}
                                    />
                                    <button type="button" onClick={handleSearch} className="btn btn-secondary custom-sub-button">
                                        Search
                                    </button>
                                </label>
                            </div>
                        </div>
                    </div>
                )}


                {dataPopupVisible && (
                    <div className="popup">
                        <div className="popup-content">
                            <h2>Data for ID: {searchID}</h2>
                            <pre>{JSON.stringify(searchedData, null, 2)}</pre>
                            <button className="btn btn-secondary custom-sub-button" onClick={() => setDataPopupVisible(false)}>
                                Close</button>

                            <button
                                className="btn btn-primary custom-sub-button"
                                onClick={handleEditClick}
                            >
                                Edit
                            </button>
                            <button
                                className="btn btn-success custom-sub-button"
                                onClick={handleSaveClick}
                            >
                                Save
                            </button>



                        </div>
                    </div>
                )}

            </div>
        </div>
    );
};

export default Event;