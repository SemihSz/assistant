console.log("You are in the deneme script");

let image;

async function getCategoryList(id) {
    const urls = 'http://localhost:8080/blog/rest-api/' + id;
    const response = await fetch(urls);
    const imageData = await response.json();
    console.log(imageData.urlImageLink);
    image = imageData.urlImageLink.toString();

}

function getImage() {
    return image;
}
