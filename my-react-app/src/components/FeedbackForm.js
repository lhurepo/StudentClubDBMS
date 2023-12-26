import React, { useState } from "react";
import DeleteForm from "./DeleteForm";
import SearchID from "../function/SearchID";

//FeedbackForm ( formID : int )
const FeedbackForm = () => {
    const [popupVisible, setPopupVisible] = useState(false);
    const [formData, setFormData] = useState({
        formID: '',


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
            formID: '',
        });
    };

    return (
        <div>
            <div><button type="button" className="btn btn-secondary custom-sub-button" onClick={handleButtonClick}>
                Update FeedbackForm
            </button>
                {popupVisible && (
                    <div className="popup" >
                        <div className="popup-content">
                            <form className="lg:w-[50%] w-[35%]" onSubmit={handleSubmit} >
                                <label className="form-title"> Insert New Data :</label><br />
                                <label className="labelStyle">
                                    Form ID :
                                    <input
                                        type="number"
                                        name="formID"
                                        value={formData.formID}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <div  >
                                    <button type="reset" onClick={handleReset} className="btn btn-secondary custom-sub-button">
                                        Clear </button>
                                    <button type="submit" onClick={handleSubmit} className="btn btn-secondary custom-sub-button">
                                        Submit </button>
                                    <button onClick={closePopup} className="btn btn-secondary custom-sub-button">Close</button>
                                </div>
                            </form>
                            <div><DeleteForm /> </div>
                            <div><SearchID eventDataUrl="https://mocki.io/v1/8de749a8-aaff-4226-ad8c-7d9bafa586a1" /></div>

                        </div>
                    </div>
                )}
            </div>


        </div>);
};

export default FeedbackForm;