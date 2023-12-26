import React, { useEffect, useState } from 'react';
import Home from "./Home";

import DisplayEvent from "./render/DisplayEvent";
import DisplayVenue from "./render/DisplayVenue";
import DisplayStu from "./render/DisplayStu";
import DisplayEventDirector from "./render/DisplayEventDerictor";
import DisplayMarketingDirector from "./render/DisplayMarketingDerictor";
import DisplayPartnershipDirector from "./render/DisplayPartnershipDerictor";
import DisplayDelegate from "./render/DisplayDelegate";
import DisplayFeedbackForm from "./render/DisplayFeedbackForm";
import DisplayProposal from "./render/DisplayProposal";
import DisplayTicket from "./render/DisplayTicket";
import DisplayInfographic from "./render/DisplayInfographic";


function App() {
    const [selectedCategory, setSelectedCategory] = useState(null);

    const handleCategoryClick = (category) => {
        setSelectedCategory(category);
    };
    const [showLogin, setShowLogin] = useState(true);




    const renderCategoryContent = () => {
        switch (selectedCategory) {
            case 'Event':
                return (
                    <div><DisplayEvent /> </div>
                );
            case 'EventDirector':
                return (
                    <div><DisplayEventDirector /></div>
                );
            case 'MarketingDirector':
                return (
                    <div><DisplayMarketingDirector /> </div>
                );
            case 'PartnershipDirector':
                return (
                    <div><DisplayPartnershipDirector /></div>
                );
            case 'Proposal':
                return (
                    <div><DisplayProposal /></div>
                );
            case 'Venue':
                return (
                    <div><DisplayVenue /></div>
                );
            case 'Infographic':
                return (
                    <div><DisplayInfographic /></div>
                );
            case 'Ticket':
                return (
                    <div><DisplayTicket /></div>
                );
            case 'FeedbackForm':
                return (
                    <div><DisplayFeedbackForm /></div>
                );
            case 'Student':
                return (
                    <div>
                        <div><DisplayStu /></div>
                    </div>
                );
            case 'Delegate':
                return (
                    <div><DisplayDelegate /></div>
                );
            default:
                return null;
        }
    };

    return (
        <div style={{ height: '100vh', display: 'flex', flexDirection: 'column' }}>
            <Home />
            <div>
                {/*<DisplayEvent />*/}


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
}

export default App;
