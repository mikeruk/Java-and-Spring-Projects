async function loadData() {
    let data;
    try {
        let response = await fetch('http://localhost/monitoring-rest-api');

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        data = await response.json();
        seedData(data);
    } catch (err) {
        console.error(err.message);
    }

}

function seedData(rawData) {
    let arrData = rawData['_embedded']['serverList'];

    const tableBody = document.getElementById("table-body");
    // Clear existing table content
    tableBody.innerHTML = '';
    arrData.forEach(data => {
        const url = new URL(data.url);
        const hostname = url.hostname;

        // Fill the table
        const tableBody = document.getElementById("table-body");

        // Create a row for the table
        const row = document.createElement("tr");

        // Create and append the server column
        const serverColumn = document.createElement("td");
        serverColumn.style.width = '100px';
        const serverNamePretty = document.createElement('pre');
        serverNamePretty.className="language-javascript";
        serverNamePretty.style.fontSize = '13px';
        serverNamePretty.textContent = hostname;
        serverColumn.appendChild(serverNamePretty);
        row.appendChild(serverColumn);

        // Create and append the status column
        const statusColumn = document.createElement("td");

        const serverStatusPretty = document.createElement('pre');
        serverStatusPretty.className="language-javascript";
        serverStatusPretty.style.fontSize = '13px';
        serverStatusPretty.textContent = JSON.stringify(data, null, 2);
        statusColumn.appendChild(serverStatusPretty);
        row.appendChild(statusColumn);

        // Append the row to the table body
        tableBody.appendChild(row);

    })
}


loadData();

setInterval(loadData, 30000);








