import React from "react"

class DisplayVenue extends React.Component {
    constructor(props) {
        super(props);
        this.state =  {
            list:[]
        }

        this.callAPI = this.callAPI.bind(this)
        this.callAPI();
    }


    // callAPI() {
    //     fetch("https://mocki.io/v1/8de749a8-aaff-4226-ad8c-7d9bafa586a1").then(
    //     ).then((data)=>{
    //             console.log(data)
    //             this.setState({
    //                 list:data.data
    //             })
    //         }
    //     )
    // }


    callAPI() {
        fetch("https://mocki.io/v1/8de749a8-aaff-4226-ad8c-7d9bafa586a1")
            .then(response => response.json())
            .then(data => {
                console.log(data);
                this.setState({
                    list: data
                });
            })
            .catch(error => {
                console.error("Error fetching data:", error);
            });
    }
//Event ( eventID : int, eventName : varchar, eventDate : date, eventTime : time, venueID : varchar )

    render() {
        let tb_data;

        if (this.state.list && this.state.list.length > 0) {
            tb_data = this.state.list.map((item) => (
                <tr key={item.id}>
                    <td>{item.venueID}</td>
                    <td>{item.address}</td>
                    <td>{item.roomNumber}</td>
                    <td>{item.capacity}</td>
                    <td>{item.bookingDate}</td>
                    <td>{item.bookingTime}</td>


                </tr>
            ));
        } else {
            tb_data = (
                <tr>
                    <td colSpan="5">No data available</td>
                </tr>
            );
        }

        return (
            <div className="container">
                <table className="table table-striped">
                    <thead>
                    <tr>

                        <th>venueID</th>
                        <th>address</th>
                        <th>roomNumber</th>
                        <th>capacity</th>
                        <th>bookingDate</th>
                        <th>bookingTime</th>

                    </tr>
                    </thead>
                    <tbody>{tb_data}</tbody>
                </table>
            </div>
        );
    }


}

export default DisplayVenue;

//Venue ( venueID : int, address : varchar, roomNumber : int, capacity : int, bookingDate : date, bookingTime : time, )