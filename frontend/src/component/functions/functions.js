export async function start() {
    const response = await fetch('/mancala/start');
    if(response.ok)
        return await response.json();
}

export async function moveApi(pitId) {
    const response = await fetch(`/mancala/move?pitId=${pitId}`)
    if(response.ok)
        return await response.json();
}