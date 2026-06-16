import React from 'react'
import  {useState} from 'react';

async function getPlayer(id) {
    const url = '/api/v1/player/';
    const [player, setPlayer] = useState([]);

    useEffect(() => {
            fetch(url + id)

    }, [])
}

export default getPlayer