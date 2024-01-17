import Recipe from "./models/Recipe";
import Profile from "./components/Profile";

export default class ApiHelper {
    static apiUrl = "https://culinary-chaos-backend.onrender.com/api"

    //Returns the list of all recipes
    static GetRecipes() {
        try {
            const url = this.apiUrl + '/recipe/allRecipes'

            return fetch(url).then(r => r.json()).then(json => {
                let recipes = []

                //Converting JSON recipes to a recipe model
                for (const r of json) {
                    let recipe = new Recipe(r.rid, r.uid, r.name, r.category, r.ingr, r.instr, r.origin, r.tags, r.iurl, r.vurl, r.preptime, r.likes)
                    recipes.push(recipe)
                }
                return recipes
            })
        } catch (e) {
            console.log(e)
        }
    }
    //returns recipe with that rid
    static GetRecipebyRid(rid) {
        try {
            const url = this.apiUrl + '/recipe/' + rid
            return fetch(url).then(r => r.json()).then(r => {
                let recipe = new Recipe(r.rid, r.uid, r.name, r.category, r.ingr, r.instr, r.origin, r.tags, r.iurl, r.vurl, r.preptime, r.likes)
                return recipe
            })
        } catch (e) {
            console.log(e)
        }
    }
    //returns all liked recipes from this user
    static GetLikedRecipes(uid) {
        try {
            const url = this.apiUrl + '/recipe/allRecipes/' + uid

            return fetch(url).then(r => r.json()).then(json => {
                let recipes = []
                //Converting JSON recipes to a recipe model
                for (const r of json) {
                    let recipe = new Recipe(r.rid, r.uid, r.name, r.category, r.ingr, r.instr, r.origin, r.tags, r.iurl, r.vurl, r.preptime, r.likes)
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
    // creates a new recipe
    static CreateRecipe(name, category, ingr, instr, origin, tags, iurl, vurl, preptime){
        let body = JSON.stringify({
            username: localStorage.getItem("username"),
            name: name,
            category: category,
            ingr: ingr,
            instr: instr,
            origin: origin,
            tags: tags,
            iurl: iurl,
            vurl: vurl,
            preptime: preptime,
            likes: 0
        })
        console.log(body)
        try {
            const url = this.apiUrl + '/recipe'
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
    
    //gets profile informations based on its UID
    static ProfileByUID(uid) {
        try {
            const url = this.apiUrl + 'profile/userid/{uid}'
            return fetch(url).then(r => r.json())
        } catch (e) {
            console.log(e)
        }
    }

    //gets profile information based on its username
    static ProfileByUsername(username) {
        try {
            const url = this.apiUrl + `/profile/username/` + username
            return fetch(url)
        } catch (e) {
            console.log(e)
        }
    }
    //get UID from username
    static GetUIDFromUsername(username) {
        try {
            const url = this.apiUrl + "/profile/username/" + username 
            return fetch(url).then(r => r.json()).uid
        } catch (e) {
            console.log(e)
        }
    }


}