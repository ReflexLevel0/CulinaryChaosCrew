import Recipe from "./models/Recipe";

export default class ApiHelper {
    static apiUrl = "http://localhost:8080"

    //Returns the list of all recipes
    static GetRecipes() {
        try {
            const url = this.apiUrl + '/recipes/allRecipes'

            return fetch(url).then(r => r.json()).then(json => {
                let recipes = []

                //Converting JSON recipes to a recipe model
                for (const r of json) {
                    let recipe = new Recipe(r.rid, r.uid, r.name, r.category, r.ingr, r.instr, r.origin, r.tags, r.url, r.likes)
                    recipes.push(recipe)
                }
                return recipes
            })
        } catch (e) {
            console.log(e)
        }
    }

    //Logs the user into the account
    static Login(username, password) {
        try {
            const url = this.apiUrl + '/profile/login'
            return fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify({
                    username: username,
                    password: password
                })
            })
        } catch (e) {
            console.log(e)
        }
    }

    //Creates a new account with the specified information
    static Register(username, password, email){
        let body = JSON.stringify({
            username: username,
            password: password,
            email: email
        })
        console.log(body)
        try {
            const url = this.apiUrl + '/profile/register'
            return fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: body
            })
        } catch (e) {
            console.log(e)
        }
    }
}