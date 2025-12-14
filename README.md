# Compliance Fee Lookup Service

A generic service that identifies which compliance fees apply for a given **UPC number** and **state code**.

This service allows users to maintain a list of compliance fees (e.g., e-waste fee, battery recycling fee), and dynamically checks whether a product is subject to one or more compliance fees based on the supplied UPC and state.

On the backend, the service uses a **Gemini AI model** to look up product information from the UPC and then evaluates which compliance fees apply.

---

## 🔍 Overview

This service does the following:

- Accepts a **UPC number** and a **state code** (U.S. state) as inputs.
- Uses a **Gemini model** to retrieve product details based on the UPC.
- Compares the product against a **user-maintained compliance fee list**.
- Returns the **applicable compliance fees** for that product in the given state.

> 💡 The compliance fee list is fully customizable — users can add, update, or remove fees as needed without modifying application code.

---

## 🚀 Features

- ✅ Lookup product details using Gemini model
- ✅ Support for multiple compliance fees
- ✅ User-maintained fee configuration
- ✅ Returns a list of relevant fees based on UPC and state

---
