import { useEffect, useState } from 'react'
import './App.css'
const API_BASE = '/api/v1/player'

function App() {

    const [players, setPlayers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        async function loadPlayers(){
            try {
                const response = await fetch(API_BASE);
                if(!response.ok) {
                    throw new Error("failed to load players");
                }
                const data = await response.json();
                setPlayers(data);
            } catch(error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        }
        loadPlayers();
    }, [])

  return(
      <main className="app">
        <section className="panel">
            <h1>Premier League Players</h1>
            <p>Showing players loaded from Spring Boot backend</p>
        </section>

        <section className="panel">
        {loading && <p>Loading players...</p>}
        
        {error && <p className = "error"> {error} </p>}

        {!loading && !error && (
            <div className = "table-wrap">
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Nation</th>
                            <th>Position</th>
                            <th>Age</th>
                            <th>Matches</th>
                            <th>Starts</th>
                            <th>Minutes</th>
                            <th>Goals</th>
                            <th>Assists</th>
                            <th>Penalties</th>
                            <th>Yellow</th>
                            <th>Red</th>
                            <th>Expected Goals</th>
                            <th>Expected Assists</th>
                            <th>Team Name</th>
                        </tr>
                    </thead>

                    <tbody>
                        {players.map((player) => (
                            <tr key={player.name}>
                                <td>{player.name}</td>
                                <td>{player.nation}</td>
                                <td>{player.position}</td>
                                <td>{player.age}</td>
                                <td>{player.matches}</td>
                                <td>{player.starts}</td>
                                <td>{player.minutes}</td>
                                <td>{player.goals}</td>
                                <td>{player.assists}</td>
                                <td>{player.penalties}</td>
                                <td>{player.yellow}</td>
                                <td>{player.red}</td>
                                <td>{player.expectedGoals}</td>
                                <td>{player.expectedAssists}</td>
                                <td>{player.teamName}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        )}
        </section>
      </main>
  );
}

export default App
