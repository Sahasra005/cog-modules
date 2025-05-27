
console.log("Welcome to the Community Portal");


class Event {
  constructor(name, date, seats, category, image) {
    this.name = name;
    this.date = date;
    this.seats = seats;
    this.category = category;
    this.image = image;
  }

  checkAvailability() {
    const eventDate = new Date(this.date);
    const today = new Date();
    return this.seats > 0 && eventDate > today;
  }
}


function registrationTracker() {
  const counts = {};
  return function (category) {
    counts[category] = (counts[category] || 0) + 1;
    return counts[category];
  };
}
const trackRegistration = registrationTracker();


const events = [
  new Event("Music Fest", "2025-06-15", 5, "Music", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6V7Y0fCRy-2DHZPFdW-4PVb30bswgrikl5Q&s"),
  new Event("Food Carnival", "2025-07-20", 10, "Food", "https://static.toiimg.com/photo/62441713.cms"),
  new Event("Health Workshop", "2025-08-10", 10, "Health", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTB5iKnJIDfmckdXRdNx7jOlr8NZpKPIjlxwg&s"),
  new Event("Community Cleanup", "2025-09-05", 15, "Social", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKdoPVQHHtdhr9urtrfaxntPfpJopWK2e_eQ&s")
];

function displayEvents(eventList) {
  const container = document.getElementById("eventContainer");
  container.innerHTML = "";

  const eventSelect = document.getElementById("eventSelect");
  eventSelect.innerHTML = '<option value="">Select Event</option>';

  [...eventList].forEach(event => {
    if (event.checkAvailability()) {
      const card = document.createElement("div");
      card.className = "event-card";

      const { name, date, seats, category, image } = event;

      card.innerHTML = `
        <img src="${image}" alt="${name}" />
        <h3>${name}</h3>
        <p><strong>Date:</strong> ${date}</p>
        <p><strong>Seats Available:</strong> ${seats}</p>
        <p><strong>Category:</strong> ${category}</p>
        <button onclick="safeRegisterUser('${name}')">Register</button>
      `;

      container.appendChild(card);

      const option = document.createElement("option");
      option.value = name;
      option.textContent = name;
      eventSelect.appendChild(option);
    }
  });
}

function safeRegisterUser(eventName) {
  try {
    registerUser(eventName);
  } catch (error) {
    alert("An error occurred: " + error.message);
  }
}


function registerUser(eventName) {
  const event = events.find(e => e.name === eventName);
  if (event && event.seats > 0) {
    event.seats--;
    trackRegistration(event.category);
    alert(`Successfully registered for ${event.name}!`);
    displayEvents(events);
  } else {
    alert("Registration failed. No seats available.");
  }
}

function simulateRegisterAPI(data) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const success = true;
      if (success) {
        resolve({ status: 200, message: "Registered Successfully" });
      } else {
        reject(new Error("API Error"));
      }
    }, 1500);
  });
}

function pageLoaded() {
  alert("Page Fully Loaded!");
  displayEvents(events);
}

document.getElementById("registerForm").addEventListener("submit", async function (e) {
  e.preventDefault();
  const name = e.target.elements.name.value.trim();
  const email = e.target.elements.email.value.trim();
  const selectedEvent = e.target.elements.event.value;
  const message = document.getElementById("formMessage");

  if (!name || !email || !selectedEvent) {
    message.textContent = "Please fill in all fields.";
    message.style.color = "red";
    return;
  }

  const event = events.find(e => e.name === selectedEvent);
  if (!event || event.seats <= 0) {
    message.textContent = "Registration failed. No seats available.";
    message.style.color = "red";
    return;
  }

  try {
    const response = await simulateRegisterAPI({ name, email, selectedEvent });
    if (response.status === 200) {
      event.seats--;
      trackRegistration(event.category);
      displayEvents(events);
      e.target.reset();
      message.textContent = `Thank you, ${name}! Registered for ${selectedEvent}.`;
      message.style.color = "green";
    }
  } catch (err) {
    message.textContent = "Error during registration.";
    message.style.color = "red";
  }
});


document.getElementById("categoryFilter").addEventListener("change", function (e) {
  const category = e.target.value;
  if (category === "All") {
    displayEvents(events);
  } else {
    const filteredEvents = [...events].filter(event => event.category === category);
    displayEvents(filteredEvents);
  }
});

ddocument.addEventListener("keydown", function (e) {
  // Ignore key presses inside input fields or textareas
  const tag = e.target.tagName.toLowerCase();
  if (tag === "input" || tag === "textarea" || e.target.isContentEditable) {
    return; // Don't filter if typing in a form field
  }

  const key = e.key.toLowerCase();
  const filtered = [...events].filter(event =>
    event.name.toLowerCase().startsWith(key) && event.checkAvailability()
  );
  if (filtered.length > 0) {
    displayEvents(filtered);
  }
});

