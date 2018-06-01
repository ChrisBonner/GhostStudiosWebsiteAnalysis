# Ghost Studios Website Analysis
Website Analysis App


This App built-in Android Studio - Still in progress - is designed for a user to easily enter a website URL and search term. The App will review the webpages HTML to determine if it has a Title, Meta description, all images have an alt attribute and proper use of the h1 tag. Also, it integrates with Google Mobile-Friendly API and checks if the website is mobile friendly.

If a search query is entered, it will check Google and extract the top 20 results, then remove any domains in the blacklist and get the top 3 results. Once the top 3 results are returned, it will analyze all 3 sites plus the URL entered and compare them together.

##### Install / Clone Repo Notes #####
You will need to create a secrets.properties file in the root folder. In that file, you will need to add in your API key like: API_KEY=my_awesome_api_key

##### Additional Notes ######
The App is still in development and will be cleaning up and making some drastic changes to the code to avoid the need for duplicate code, which is currently the case.
