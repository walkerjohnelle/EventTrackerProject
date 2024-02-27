document.addEventListener('DOMContentLoaded', function() {
    init();
});

function init() {
    document.getElementById('searchButton').addEventListener('click', searchShows);
    document.getElementById('addShowButton').addEventListener('click', showNewShowForm);
    document.getElementById('newShowForm').addEventListener('submit', addNewShow);
    document.getElementById('newRatingForm').addEventListener('submit', addNewRating);

    getTvShows();
    getRatings();
}

function getTvShows() {
    fetch('api/shows')
        .then(response => response.json())
        .then(tvShows => {
            displayTvShows(tvShows);
        })
        .catch(error => {
            console.error('Error fetching TV shows:', error);
        });
}

function displayTvShows(tvShows) {
    let tvShowList = document.getElementById('tvShowList');
    tvShowList.innerHTML = '';

    tvShows.forEach(tvShow => {
        let row = document.createElement('tr');
        row.innerHTML = `
            <td>${tvShow.title}</td>
            <td>${tvShow.genre}</td>
            <td>${tvShow.description}</td>
            <td>${tvShow.releaseYear}</td>
            <td>${tvShow.seasons}</td>
            <td>${tvShow.totalEpisodes}</td>
            <td>${tvShow.streamingPlatform}</td>
            <td>
                <button class="btn btn-sm btn-primary" onclick="editTvShow(${tvShow.id})">Edit</button>
                <button class="btn btn-sm btn-danger" onclick="deleteTvShow(${tvShow.id})">Delete</button>
            </td>
        `;
        tvShowList.appendChild(row);
    });
}

function getRatings() {
    fetch('api/ratings')
        .then(response => response.json())
        .then(ratings => {
            displayRatings(ratings);
        })
        .catch(error => {
            console.error('Error fetching ratings:', error);
        });
}

function displayRatings(ratings) {
    let ratingsList = document.getElementById('ratingsList');
    ratingsList.innerHTML = '';

    ratings.forEach(rating => {
        let row = document.createElement('tr');
        row.innerHTML = `
            <td>${rating.rating}</td>
            <td>${rating.review}</td>
        `;
        ratingsList.appendChild(row);
    });
}

function searchShows() {
    let keyword = document.getElementById('searchKeyword').value.trim();
    if (keyword !== '') {
        fetch(`api/shows/search/${encodeURIComponent(keyword)}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Error searching shows');
                }
            })
            .then(shows => {
                displaySearchResults(shows);
            })
            .catch(error => {
                console.error(error);
            });
    }
}

function displaySearchResults(shows) {
    let searchResultsDiv = document.getElementById('searchResults');
    searchResultsDiv.innerHTML = '';

    if (shows.length === 0) {
        searchResultsDiv.textContent = 'No shows found';
        return;
    }

    let table = document.createElement('table');
    table.classList.add('table');

    let thead = document.createElement('thead');
    let headerRow = document.createElement('tr');
    let headers = ['Title', 'Genre', 'Description', 'Release Year', 'Seasons', 'Total Episodes', 'Platform'];
    for (let headerText of headers) {
        let th = document.createElement('th');
        th.textContent = headerText;
        headerRow.appendChild(th);
    }
    thead.appendChild(headerRow);
    table.appendChild(thead);

    let tbody = document.createElement('tbody');
    for (let show of shows) {
        let row = document.createElement('tr');
        row.innerHTML = `
            <td>${show.title}</td>
            <td>${show.genre}</td>
            <td>${show.description}</td>
            <td>${show.releaseYear}</td>
            <td>${show.seasons}</td>
            <td>${show.totalEpisodes}</td>
            <td>${show.streamingPlatform}</td>
        `;
        tbody.appendChild(row);
    }
    table.appendChild(tbody);

    searchResultsDiv.appendChild(table);
}

function showNewShowForm() {
    document.getElementById('newShowFormContainer').style.display = 'block';
}

function addNewShow(event) {
    event.preventDefault();

    let newShow = {
        title: document.getElementById('showTitle').value,
        genre: document.getElementById('showGenre').value,
        description: document.getElementById('showDescription').value,
        releaseYear: parseInt(document.getElementById('showReleaseYear').value),
        seasons: parseInt(document.getElementById('showSeasons').value),
        totalEpisodes: parseInt(document.getElementById('showEpisodes').value),
        streamingPlatform: document.getElementById('showStreamingService').value
    };

    fetch('api/shows', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newShow)
    })
    .then(response => {
        if (response.ok) {
            getTvShows();
            document.getElementById('newShowFormContainer').style.display = 'none';
        } else {
            throw new Error('Failed to add new show');
        }
    })
    .catch(error => {
        console.error(error);
    });
}

function addNewRating(event) {
    event.preventDefault();

    let newRating = {
        rating: parseFloat(document.getElementById('ratingValue').value),
        review: document.getElementById('ratingReview').value
    };

    // Assume showId is available in the context or fetched from somewhere
    let showId = 1; // Replace 1 with the actual showId

    fetch(`api/ratings/shows/${showId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newRating)
    })
    .then(response => {
        if (response.ok) {
            getRatings();
            document.getElementById('newRatingFormContainer').style.display = 'none';
        } else {
            throw new Error('Failed to add new rating');
        }
    })
    .catch(error => {
        console.error(error);
    });
}

function editTvShow(tvShowId) {
	document.getElementById('tvShowId').value = tvShowId;

	fetch(`api/shows/${tvShowId}`)
		.then(response => response.json())
		.then(tvShow => {
			document.getElementById('title').value = tvShow.title;
			document.getElementById('description').value = tvShow.description;
			document.getElementById('genre').value = tvShow.genre;
			document.getElementById('releaseYear').value = tvShow.releaseYear;
			document.getElementById('seasons').value = tvShow.seasons;
			document.getElementById('totalEpisodes').value = tvShow.totalEpisodes;
			document.getElementById('streamingPlatform').value = tvShow.streamingPlatform;
			document.querySelector(`input[name="active"][value="${tvShow.active}"]`).checked = true;

		})
		.catch(error => {
			console.error('Error fetching TV show details:', error);
		});
}
function deleteTvShow(tvShowId) {
	fetch(`api/shows/${tvShowId}`, {
		method: 'DELETE'
	})
		.then(response => {
			if (response.ok) {
				getTvShows();
			} else {
				console.error('Failed to delete TV show');
			}
		})
		.catch(error => {
			console.error('Error deleting TV show:', error);
		});
}