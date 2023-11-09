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
}