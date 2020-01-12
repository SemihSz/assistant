async function getDeleteAllTodoList() {
    const urls = 'http://localhost:8080/home/list-all';
    const response = await fetch(urls);
    const data = await response.json();
    console.log(data)
}
