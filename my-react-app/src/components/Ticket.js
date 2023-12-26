//Ticket ( ticketID : int, price : double, eventID : int )

import React, {useState} from "react";
import DeleteForm from "./DeleteForm";
import SearchID from "../function/SearchID";

const Ticket = () => {
    const [popupVisible, setPopupVisible] = useState(false);
    const [formData, setFormData] = useState({
        filename: '',
        editingSoftware:'',
        execID: '',
        eventID:'',
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
            filename: '',
            editingSoftware:'',
            execID: '',
            eventID:'',
        });
    };

    return (
        <div>
            <div><button type="button" className="btn btn-secondary custom-sub-button" onClick ={handleButtonClick}>
                Update Ticket
            </button>
                {popupVisible && (
                    <div className="popup" >
                        <div className="popup-content">
                            <form className="lg:w-[50%] w-[35%]" onSubmit={handleSubmit} >
                                <label className="form-title"> Insert New Data :</label><br />
                                <label className="labelStyle">
                                    File Name :
                                    <input
                                        type="text"
                                        name="filename"
                                        value={formData.filename}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Software :
                                    <input
                                        type="text"
                                        name="editingSoftware"
                                        value={formData.editingSoftware}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Exec ID:
                                    <input
                                        type="text"
                                        name="execID"
                                        value={formData.execID}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Event ID:
                                    <input
                                        type="text"
                                        name="eventID"
                                        value={formData.eventID}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />
                                {/*<label className="labelStyle">*/}
                                {/*    Venue ID:*/}
                                {/*    <input*/}
                                {/*        type="text"*/}
                                {/*        name="venueID"*/}
                                {/*        value={formData.venueID}*/}
                                {/*        onChange={handleInputChange}*/}
                                {/*    />*/}
                                {/*</label>*/}
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
                            <div><SearchID/></div>

                        </div>
                    </div>
                )}
            </div>


        </div>);
};

export default Ticket;