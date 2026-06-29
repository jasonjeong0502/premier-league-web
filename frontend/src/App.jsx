import { useEffect, useState } from 'react'
import './App.css'
import PlayerTable from "./PlayerTable.jsx";
const API_BASE = '/api/v1/player'

function App() {

    const [players, setPlayers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const [searchText, setSearchText] = useState("");
    const [selectedTeam, setSelectedTeam] = useState("All Teams");
    const [sortBy, setSortBy] = useState("None")
    // maybe later add increasing order using setDirection
    // const [direction, setDirection] = useState();
    const normalizedSearchText = searchText.trim().toLowerCase()
    const teamName = [...new Set(players.map((player) => (player.teamName)))].sort()
    const filteredPlayers = players.filter((player) => {

        const playerName = player.name.toLowerCase();
        const teamName = player.teamName;

        if (normalizedSearchText === "squad total") return false
        if(normalizedSearchText === "all teams" || selectedTeam === "All Teams") return true

        const matchesName = normalizedSearchText === "" || playerName.startsWith(normalizedSearchText)
        const matchesTeam = teamName === "" || teamName.startsWith(selectedTeam)

        return matchesName && matchesTeam

    })

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
            <p> Introducing all you need to know about your favorite Premier League Players </p>
        </section>

        <section className="panel">
        {loading && <p>Loading players...</p>}
        
        {error && <p className = "error"> {error} </p>}

            <label> Search for players here </label>
            <input type="search" name="q" placeholder="search for players" value={searchText} onChange={(event) => setSearchText(event.target.value)}/>

            <select value={selectedTeam} onChange={(event) => setSelectedTeam(event.target.value)}>
                <option value="All Teams">All Teams</option>
                {teamName.map((teamName) => (<option key={teamName} value={teamName}>{teamName}</option>))}
            </select> {/* map() returns an array */}
            {/* Functional updates: putting functions in setters to retain variable value */}
            <button type="button" onClick={() => setSortBy(prev => (prev === "Goals" ? "None" : "Goals"))}>Press for Goal Sorting</button>
            <button type="button" onClick={() => setSortBy(prev => (prev === "Assists" ? "None" : "Assists"))}>Press for Assists Sorting</button>

            {!loading && !error && (
                <PlayerTable /* ... means create a new array copy / sort() can mutate original arrays[arrays that come from state/derived state - ex. filteredplayers.sort()] => can cause bugs*/
                    players={
                    sortBy === "Goals" ? [...filteredPlayers].sort((a,b) => (b.goals - a.goals))
                    : sortBy === "Assists" ? [...filteredPlayers].sort((a,b) => (b.assists - a.assists))
                    : filteredPlayers
                }
                />
        )}
        </section>
      </main>
  );
}

export default App
