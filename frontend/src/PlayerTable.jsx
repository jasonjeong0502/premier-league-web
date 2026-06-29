function PlayerTable({players}) {
    return (
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
            {players.map((player,index) => (
                <tr key={`${player.name}-${player.teamName}-${index}`}>
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
    )
}

export default PlayerTable