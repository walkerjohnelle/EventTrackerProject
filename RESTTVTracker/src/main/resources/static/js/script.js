// Function to fetch all jobs and display them
function getAllJobs() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'api/jobs', true);
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 400) {
            var jobs = JSON.parse(xhr.responseText);
            displayJobs(jobs);
        } else {
            console.error('Failed to fetch jobs');
        }
    };
    xhr.send();
}

// Function to display all jobs in a table
function displayJobs(jobs) {
    // Assuming you have a table element with id "jobTable" in your HTML
    var table = document.getElementById('jobTable');
    // Clear existing table rows
    table.innerHTML = '';
    // Create table header
    var header = table.createTHead();
    var row = header.insertRow(0);
    var headers = ['ID', 'Title', 'Description', 'Actions'];
    for (var i = 0; i < headers.length; i++) {
        var th = document.createElement('th');
        th.textContent = headers[i];
        row.appendChild(th);
    }
    // Populate table rows with job data
    for (var i = 0; i < jobs.length; i++) {
        var job = jobs[i];
        row = table.insertRow(i + 1);
        row.insertCell(0).textContent = job.id;
        row.insertCell(1).textContent = job.title;
        row.insertCell(2).textContent = job.description;
        var actionsCell = row.insertCell(3);
        // Add buttons for update and delete actions
        var updateButton = document.createElement('button');
        updateButton.textContent = 'Update';
        updateButton.addEventListener('click', function () {
            // Call function to update job passing job ID
            var jobId = job.id;
            var updatedJobData = {
                title: 'Updated Title',
                description: 'Updated Description'
            };
            updateJob(jobId, updatedJobData);
        });
        actionsCell.appendChild(updateButton);

        var deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', function () {
            // Call function to delete job passing job ID
            var jobId = job.id;
            deleteJob(jobId);
        });
        actionsCell.appendChild(deleteButton);
    }
}

// Function to create a new job
function createJob(jobData) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'api/jobs', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function () {
        if (xhr.status === 200 || xhr.status === 201) {
            var newJob = JSON.parse(xhr.responseText);
            // Optionally, you can handle the response, e.g., display the new job
            console.log('New job created:', newJob);
            // Refresh job list after creating a new job
            getAllJobs();
        } else {
            console.error('Failed to create job');
        }
    };
    xhr.send(JSON.stringify(jobData));
}

// Function to update an existing job
function updateJob(jobId, jobData) {
    var xhr = new XMLHttpRequest();
    xhr.open('PUT', 'api/jobs/' + jobId, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function () {
        if (xhr.status === 200) {
            var updatedJob = JSON.parse(xhr.responseText);
            // Optionally, you can handle the response, e.g., display the updated job
            console.log('Job updated:', updatedJob);
            // Refresh job list after updating a job
            getAllJobs();
        } else {
            console.error('Failed to update job');
        }
    };
    xhr.send(JSON.stringify(jobData));
}

// Function to delete a job
function deleteJob(jobId) {
    var xhr = new XMLHttpRequest();
    xhr.open('DELETE', 'api/jobs/' + jobId, true);
    xhr.onload = function () {
        if (xhr.status === 204) {
            // Optionally, you can handle the response, e.g., display a success message
            console.log('Job deleted successfully');
            // Refresh job list after deleting a job
            getAllJobs();
        } else {
            console.error('Failed to delete job');
        }
    };
    xhr.send();
}

// Call getAllJobs function when the page loads to fetch and display all jobs
window.onload = function () {
    getAllJobs();
};
