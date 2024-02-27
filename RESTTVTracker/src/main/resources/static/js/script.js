document.addEventListener('DOMContentLoaded', function() {
	init();
});

function init() {
	let tvShowIdInput = document.createElement('input');
	tvShowIdInput.type = 'hidden';
	tvShowIdInput.id = 'tvShowId';
	document.body.appendChild(tvShowIdInput);

	document.getElementById('searchButton').addEventListener('click', searchShows);
	document.getElementById('addShowButton').addEventListener('click', showNewShowForm);
	document.getElementById('newShowForm').addEventListener('submit', addNewShow);
	document.getElementById('newRatingForm').addEventListener('submit', createNewRating);

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
			<button class="btn btn-sm btn-primary" onclick="showNewRatingForm(${tvShow.id})">Add Rating</button>
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
function showNewRatingForm(tvShowId) {
    document.getElementById("selectedTvShowId").value = tvShowId;
    document.getElementById("newRatingFormContainer").style.display = "block";
}


function createNewRating() {
	let showId = document.getElementById('selectedTvShowId').value;

	let newRating = {
		rating: parseFloat(document.getElementById('ratingValue').value),
		review: document.getElementById('ratingReview').value,
		tvShow: {
			id: parseInt(showId)
		},
		user: {
			id: 1
		}
	};
	addNewRating(newRating);
}

function addNewRating(newRating) {

	let xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/ratings');
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.onreadystatechange = function() {
		if (xhr.status >= 200 && xhr.status < 300) {
			getRatings();

			document.getElementById('newRatingFormContainer').style.display = 'block';
		} else {
			alert('Failed to add new rating');
		}
	};
	xhr.onerror = function() {
		console.error('Error occurred while sending the request');
	};
	xhr.send(JSON.stringify(newRating));
}
function editTvShow(tvShowId) {
    fetchTvShowDetails(tvShowId);
}


function fetchTvShowDetails(tvShowId) {
    fetch(`api/shows/${tvShowId}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Failed to fetch TV show details');
            }
        })
        .then(tvShow => {
            populateTvShowFields(tvShow);
        })
        .catch(error => {
            console.error('Error fetching TV show details:', error);
        });
}
function editTvShow(tvShowId) {
    fetch(`api/shows/${tvShowId}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Failed to fetch TV show details');
            }
        })
        .then(tvShow => {
            populateUpdateForm(tvShow);
        })
        .catch(error => {
            console.error('Error fetching TV show details:', error);
        });
}

function populateUpdateForm(tvShow) {
    document.getElementById('updatedTvShowId').value = tvShow.id;
    document.getElementById('updatedShowTitle').value = tvShow.title;
    document.getElementById('updatedShowGenre').value = tvShow.genre;
    document.getElementById('updatedShowDescription').value = tvShow.description;
    document.getElementById('updatedShowReleaseYear').value = tvShow.releaseYear;
    document.getElementById('updatedShowSeasons').value = tvShow.seasons;
    document.getElementById('updatedShowEpisodes').value = tvShow.totalEpisodes;
    document.getElementById('updatedShowStreamingService').value = tvShow.streamingPlatform;
    document.getElementById('updateShowFormContainer').style.display = 'block';
}

function updateTvShow() {
    let updatedTvShow = {
        id: document.getElementById('updatedTvShowId').value,
        title: document.getElementById('updatedShowTitle').value,
        genre: document.getElementById('updatedShowGenre').value,
        description: document.getElementById('updatedShowDescription').value,
        releaseYear: parseInt(document.getElementById('updatedShowReleaseYear').value),
        seasons: parseInt(document.getElementById('updatedShowSeasons').value),
        totalEpisodes: parseInt(document.getElementById('updatedShowEpisodes').value),
        streamingPlatform: document.getElementById('updatedShowStreamingService').value
    };

    fetch(`api/shows/${updatedTvShow.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedTvShow)
    })
    .then(response => {
        if (response.ok) {
            alert('TV show updated successfully');
            hideUpdateForm();
            getTvShows(); 
        } else {
            throw new Error('Failed to update TV show');
        }
    })
    .catch(error => {
        console.error('Error updating TV show:', error);
        alert('Failed to update TV show');
    });
}

function hideUpdateForm() {
    document.getElementById('updateShowFormContainer').style.display = 'none';
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

function showNewRatingForm() {
	document.getElementById('newRatingFormContainer').style.display = 'block';
}
function updateRating(ratingId) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `api/ratings/${ratingId}`);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let rating = JSON.parse(xhr.responseText);
            document.getElementById('updatedRatingValue').value = rating.rating;
            document.getElementById('updatedRatingReview').value = rating.review;
        } else if (xhr.status !== 200) { 
            console.error('Error fetching rating details:', xhr.status, xhr.statusText);
            alert('Failed to fetch rating details');
        } 
    };
    xhr.onerror = function() {
        console.error('Request failed');
    };
    xhr.send();
}

function updateTvShow() {
  let updatedTvShow = {
    id: document.getElementById('updatedTvShowId').value,
    title: document.getElementById('updatedShowTitle').value,
    genre: document.getElementById('updatedShowGenre').value,
    description: document.getElementById('updatedShowDescription').value,
    releaseYear: parseInt(document.getElementById('updatedShowReleaseYear').value),
    seasons: parseInt(document.getElementById('updatedShowSeasons').value),
    totalEpisodes: parseInt(document.getElementById('updatedShowEpisodes').value),
    streamingPlatform: document.getElementById('updatedShowStreamingService').value
  };

  let xhr = new XMLHttpRequest();
  xhr.open('PUT', `api/shows/${updatedTvShow.id}`);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      alert('TV show updated successfully');
      hideUpdateForm();
      getTvShows(); 
    } else if (xhr.status !== 200) {
      console.error('Error updating TV show:', xhr.status, xhr.statusText);
      alert('Failed to update TV show');
    }
  };
  xhr.onerror = function() {
      console.error('Request failed');
  };
  xhr.send(JSON.stringify(updatedTvShow));
}

function deleteRating(ratingId) {
  let xhr = new XMLHttpRequest();
  xhr.open('DELETE', `api/ratings/${ratingId}`);
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      getRatings(); 
    } else if (xhr.status !== 200) {
      console.error('Failed to delete rating:', xhr.status, xhr.statusText);
    } 
  };
  xhr.onerror = function() {
      console.error('Request failed');
  };
  xhr.send();
}
