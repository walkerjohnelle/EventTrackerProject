document.addEventListener('DOMContentLoaded', function() {
	init();
});

function init() {
	document.getElementById('showCreateFormBtn').addEventListener('click', showCreateForm);
	document.getElementById('ratingCreateFormBtn').addEventListener('click', ratingCreateForm);
	document.getElementById('searchBtn').addEventListener('click', searchShows);
	getTvShows();
	getRatings();

	let editButtons = document.querySelectorAll('.edit-btn');
	editButtons.forEach(button => {
		button.addEventListener('click', function() {
			let tvShowId = this.dataset.id;
			editTvShow(tvShowId);
		});
	});

	document.getElementById('ratingCreateFormBtn').addEventListener('click', showRatingCreateForm);

	document.getElementById('submitRatingBtn').addEventListener('click', addNewRating);

	let ratingEditButtons = document.querySelectorAll('.edit-rating-btn');
	ratingEditButtons.forEach(button => {
		button.addEventListener('click', function() {
			let ratingId = this.dataset.id;
			editRating(ratingId);
		});
	});

	let deleteButtons = document.querySelectorAll('.delete-rating-btn');
	deleteButtons.forEach(button => {
		button.addEventListener('click', function() {
			let ratingId = this.dataset.id;
			deleteRating(ratingId);
		});
	});

	let showRatingButtons = document.querySelectorAll('.show-rating-btn');
	showRatingButtons.forEach(button => {
		button.addEventListener('click', function() {
			let showId = this.dataset.id;
			showRatingCreateForm(showId);
		});
	});
}

function showRatingCreateForm(showId) {
	document.getElementById('newRatingForm').style.display = 'block';
	document.getElementById('submitRatingBtn').dataset.showId = showId;
}

function addNewRating() {
	let showId = document.getElementById('submitRatingBtn').dataset.showId;
	let newRating = {
		rating: document.getElementById('rating').value,
		review: document.getElementById('review').value
	};
	addRating(showId, newRating);
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
	let tvShowTable = document.getElementById('tvShowTable');
	tvShowTable.innerHTML = '';

	tvShows.forEach(tvShow => {
		let row = document.createElement('tr');
		row.innerHTML = `
            <td>${tvShow.title}</td>
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
		tvShowTable.appendChild(row);
	});
}

function showCreateForm() {
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
	let ratingTable = document.getElementById('ratingTable');
	ratingTable.innerHTML = '';

	ratings.forEach(rating => {
		let row = document.createElement('tr');
		row.innerHTML = `
            <td>${rating.rating}</td>
            <td>${rating.review}</td>
            <td>
                <button class="btn btn-sm btn-primary" onclick="editRating(${rating.id})">Edit</button>
                <button class="btn btn-sm btn-danger" onclick="deleteRating(${rating.id})">Delete</button>
            </td>
        `;
		ratingTable.appendChild(row);
	});
}

function ratingCreateForm() {
}

function editRating(ratingId) {
}

function deleteRating(ratingId) {
	fetch(`api/ratings/${ratingId}`, {
		method: 'DELETE'
	})
		.then(response => {
			if (response.ok) {
				getRatings();
			} else {
				console.error('Failed to delete rating');
			}
		})
		.catch(error => {
			console.error('Error deleting rating:', error);
		});
}


function searchShows() {
	let keyword = document.getElementById('keyword').value.trim();
	if (keyword !== '') {
		let xhr = new XMLHttpRequest();
		xhr.open('GET', 'api/shows/search/' + encodeURIComponent(keyword));

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					let shows = JSON.parse(xhr.responseText);

					if (shows.length > 0) {
						displaySearchResults(shows);
					} else {
						let searchResultsDiv = document.getElementById('searchResults');
						searchResultsDiv.textContent = 'No films found';
						searchResultsDiv.style.display = 'block';
					}
				} else {
					alert('Error searching shows');
				}
			}
		};
		xhr.send();
	}

}

function displaySearchResults(shows) {
	let searchResultsDiv = document.getElementById('searchResults');
	searchResultsDiv.textContent = '';

	if (shows.length === 0) {
		searchResultsDiv.textContent = 'No shows found';
		return;
	}

	let table = document.createElement('table');
	table.classList.add('table');

	let thead = document.createElement('thead');
	let headerRow = document.createElement('tr');
	let headers = ['Title', 'Description', 'Release Year', 'Seasons', 'Total Episodes', 'Streaming Platform'];
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
function createNewShow() {
	let newShow = {
		title: document.newShowForm.title.value,
		genre: document.newShowForm.genre.value,
		description: document.newShowForm.description.value,
		releaseYear: parseInt(document.newShowForm.releaseYear.value),
		seasons: parseInt(document.newShowForm.seasons.value),
		totalEpisodes: parseInt(document.newShowForm.totalEpisodes.value),
		active: document.newShowForm.active.checked,
		streamingPlatform: document.newShowForm.streamingPlatform.value,
	};

	let tvShowId = document.getElementById('tvShowId').value;
	if (tvShowId) {
		submitEditedTvShow(tvShowId, newShow);
	} else {
		addShow(newShow);
	}
}



function addShow(newShow) {
	let xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/shows');
	xhr.setRequestHeader('Content-Type', 'application/json');

	xhr.onreadystatechange = function() {
		console.log("Ready state:", xhr.readyState);
		if (xhr.readyState === 4) {
			console.log("Status code:", xhr.status);
			console.log("Response:", xhr.responseText);
			if (xhr.status === 201) {
				let createdShow = JSON.parse(xhr.responseText);
				displayShow(createdShow);

				document.getElementById('showData').style.display = 'block';
			} else {
				alert('Error creating new show');
			}
		}
	};
	xhr.send(JSON.stringify(newShow));
}


function displayShow(show) {
	let dataDiv = document.getElementById('showData');
	dataDiv.textContent = '';

	let title = document.createElement('h1');
	title.textContent = show.title;
	dataDiv.appendChild(title);


	let description = document.createElement('blockquote');
	description.textContent = show.description;
	dataDiv.appendChild(description);

	let detailsList = document.createElement('ul');

	let genre = document.createElement('li');
	title.textContent = show.genre;
	dataDiv.appendChild(genre);

	let releaseYearItem = document.createElement('li');
	releaseYearItem.textContent = 'Release Year: ' + show.releaseYear;
	detailsList.appendChild(releaseYearItem);

	let seasonsItem = document.createElement('li');
	seasonsItem.textContent = 'Seasons: ' + show.seasons;
	detailsList.appendChild(seasonsItem);

	let totalEpisodesItem = document.createElement('li');
	totalEpisodesItem.textContent = 'Total Episodes: ' + show.totalEpisodes;
	detailsList.appendChild(totalEpisodesItem);

	let streamingPlatformItem = document.createElement('li');
	streamingPlatformItem.textContent = 'Streaming Platform: ' + show.streamingPlatform;
	detailsList.appendChild(streamingPlatformItem);

	dataDiv.appendChild(detailsList);
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


function submitEditedTvShow(tvShowId, updatedShow) {
	fetch(`api/shows/${tvShowId}`, {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(updatedShow)
	})
		.then(response => {
			if (response.ok) {
				getTvShows();
			} else {
				console.error('Failed to update TV show');
			}
		})
		.catch(error => {
			console.error('Error updating TV show:', error);
		});
}

function addRating(showId, newRating) {
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
			} else {
				console.error('Failed to add rating');
			}
		})
		.catch(error => {
			console.error('Error adding rating:', error);
		});
}


function editRating(ratingId, updatedRating) {
	fetch(`api/ratings/${ratingId}`, {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(updatedRating)
	})
		.then(response => {
			if (response.ok) {
				getRatings();
			} else {
				console.error('Failed to update rating');
			}
		})
		.catch(error => {
			console.error('Error updating rating:', error);
		});
}

function deleteRating(ratingId) {
	fetch(`api/ratings/${ratingId}`, {
		method: 'DELETE'
	})
		.then(response => {
			if (response.ok) {
				getRatings();
			} else {
				console.error('Failed to delete rating');
			}
		})
		.catch(error => {
			console.error('Error deleting rating:', error);
		});
}
