//Venue ( venueID : int, address : varchar, roomNumber : int, capacity : int, bookingDate : date, bookingTime : time, )

import React, {useState} from "react";
import DeleteForm from "./DeleteForm";

const Venue = () => {
    const [popupVisible, setPopupVisible] = useState(false);
    const [formData, setFormData] = useState({
        venueID: '',
        address:'',
        roomNumber: '',
        capacity:'',
        bookingDate:'',
        bookingTime:'',

    })
    const handleButtonClick = () => {
        setPopupVisible(!popupVisible);
    };
    const closePopup = () => {
        setPopupVisible(false);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Form submitted:', formData);
        closePopup();
    };

    const handleReset = () => {
        setFormData({
            venueID: '',
            address:'',
            roomNumber: '',
            capacity:'',
            bookingDate:'',
            bookingTime:'',
        });
    };

    return (
        <div>
            <div><button type="button" className="btn btn-secondary custom-sub-button" onClick ={handleButtonClick}>
                Update Venue
            </button>
                {popupVisible && (
                    <div className="popup" >
                        <div className="popup-content">
                            <form className="lg:w-[50%] w-[35%]" onSubmit={handleSubmit} >
                                <label className="form-title"> Insert New Data :</label><br />
                                <label className="labelStyle">
                                    Venue ID :
                                    <input
                                        type="number"
                                        name="venueID"
                                        value={formData.venueID}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Address :
                                    <input
                                        type="text"
                                        name="address"
                                        value={formData.address}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Room No. :
                                    <input
                                        type="number"
                                        name="roomNumber"
                                        value={formData.roomNumber}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Capacity :
                                    <input
                                        type="number"
                                        name="capacity"
                                        value={formData.capacity}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />
                                <label className="labelStyle">
                                    Booking Date:
                                    <input
                                        type="date"
                                        name="bookingDate"
                                        value={formData.bookingDate}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Booking Time :
                                    <input
                                        type="time"
                                        name="bookingTime"
                                        value={formData.bookingTime}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />



                                <div  >
                                    <button type="reset" onClick={handleReset} className="btn btn-secondary custom-sub-button">
                                        Clear </button>
                                    <button type="submit"  onClick={handleSubmit} className="btn btn-secondary custom-sub-button">
                                        Submit </button>
                                    <button onClick={closePopup} className="btn btn-secondary custom-sub-button">Close</button>
                                </div>
                            </form>
                            <div><DeleteForm/> </div>

                        </div>
                    </div>
                )}
            </div>


        </div>);
};

export default Venue;