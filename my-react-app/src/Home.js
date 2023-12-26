import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Home.css';
import Event from "./components/Event";
import MarketingDirector from "./components/MarketingDirector";
import EventDirector from "./components/EventDirector";
import PartnershipDirector from "./components/PartnershipDirector";
import Infographic from "./components/Infographic";
import Ticket from "./components/Ticket";
import Venue from "./components/Venue";
import Student from "./components/Student";
import FeedbackForm from "./components/FeedbackForm";
import Delegate from "./components/Delegate";
import Proposal from "./components/Proposal";
import DisplayEvent from "./render/DisplayEvent";
import LoginWindow from "./LoginWindow";


const Home = () => {
    const [selectedCategory, setSelectedCategory] = useState(null);

    const handleCategoryClick = (category) => {
        setSelectedCategory(category);
    };


    const renderCategoryContent = () => {
        switch (selectedCategory) {
            case 'Event':
                return (
                    <div><Event/> </div>

                );
            case 'EventDirector':
                return (
                    <div><EventDirector/></div>
                );
            case 'MarketingDirector':
                return (
                    <div><MarketingDirector/> </div>
                );
            case 'PartnershipDirector':
                return (
                    <div><PartnershipDirector/></div>
                );
            case 'Proposal':
                return (
                    <div><Proposal/></div>
                );
            case 'Venue':
                return (
                    <div><Venue/></div>
                );
            case 'Infographic':
                return (
                    <div><Infographic/></div>
                );
            case 'Ticket':
                return (
                    <div><Ticket/></div>
                );
            case 'FeedbackForm':
                return (
                    <div><FeedbackForm/></div>
                );
            case 'Student':
                return (
                    <div>
                        <div><Student/></div>
                    </div>
                );
            case 'Delegate':
                return (
                    <div><Delegate/></div>
                );
            default:
                return null;
        }
    };



    return (


        <div style={{ backgroundColor: 'lightblue', height: '30%' }}>
            {/*<button onClick={() => setShowLogin(true)}>Open Login</button>*/}
            <div>
                    <LoginWindow  />
                </div>
        <div>
            <h1>Student Club Database</h1>
            <div className="App">
                <button
                    type="button"
                    className={`btn btn-primary custom-button ${selectedCategory === 'Event' ? 'active' : ''}`}
                    onClick={() => handleCategoryClick('Event')}
                >
                    Event
                </button>

                <button
                    type="button"
                    className={`btn btn-primary custom-button ${selectedCategory === 'EventDirector' ? 'active' : ''}`}
                    onClick={() => handleCategoryClick('EventDirector')}
                >
                    EventDirector
                </button>

                <button
                    type="button"
                    className={`btn btn-primary custom-button ${selectedCategory === 'MarketingDirector' ? 'active' : ''}`}
                    onClick={() => handleCategoryClick('MarketingDirector')}
                >
                    MarketingDirector
                </button>

                <button
                    type="button"
                    className={`btn btn-primary custom-button ${selectedCategory === 'PartnershipDirector' ? 'active' : ''}`}
                    onClick={() => handleCategoryClick('PartnershipDirector')}
                >
                    PartnershipDirector
                </button>

                <button
                    type="button"
                    className={`btn btn-primary custom-button ${selectedCategory === 'Proposal' ? 'active' : ''}`}
                    onClick={() => handleCategoryClick('Proposal')}
                >
                    Proposal
                </button>

                <button
                    type="button"
                    className={`btn btn-primary custom-button ${selectedCategory === 'Venue' ? 'active' : ''}`}
                    onClick={() => handleCategoryClick('Venue')}
                >
                    Venue
                </button>

                <button
                    type="button"
                    className={`btn btn-primary custom-button ${selectedCategory === 'Infographic' ? 'active' : ''}`}
                    onClick={() => handleCategoryClick('Infographic')}
                >
                    Infographic
                </button>

                <button
                    type="button"
                    className={`btn btn-primary custom-button ${selectedCategory === 'Ticket' ? 'active' : ''}`}
                    onClick={() => handleCategoryClick('Ticket')}
                >
                    Ticket
                </button>

                <button
                    type="button"
                    className={`btn btn-primary custom-button ${selectedCategory === 'FeedbackForm' ? 'active' : ''}`}
                    onClick={() => handleCategoryClick('FeedbackForm')}
                >
                    Feedback Form
                </button>

                <button
                    type="button"
                    className={`btn btn-primary custom-button ${selectedCategory === 'Student' ? 'active' : ''}`}
                    onClick={() => handleCategoryClick('Student')}
                >
                    Student
                </button>

                <button
                    type="button"
                    className={`btn btn-primary custom-button ${selectedCategory === 'Delegate' ? 'active' : ''}`}
                    onClick={() => handleCategoryClick('Delegate')}
                >
                    Delegate
                </button>


                {renderCategoryContent()}

            </div>
        </div>
        </div>

    );
};





export default Home;
