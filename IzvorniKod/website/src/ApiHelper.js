import Recipe from "./models/Recipe";
import Comment1 from "./models/Comment1";

export default class ApiHelper {
    static apiUrl = "https://culinary-chaos-backend.onrender.com/api"

    //Returns the list of all recipes
    static GetRecipes() {
        try {
            let uid = localStorage.getItem("uid")
            let url = this.apiUrl + '/recipe/allRecipes'
            if(uid !== '') url += `?loggedInUserId=${uid}`

            return fetch(url).then(r => r.json()).then(json => {
                let recipes = []

                //Converting JSON recipes to a recipe model
                for (const r of json) {
                    let recipe = new Recipe(r.rid, r.uid, r.name, r.category, r.ingr, r.instr, r.origin, r.tags, r.iurl, r.vurl, r.preparationTime, r.likes)
                    recipes.push(recipe)
                }
                return recipes
            })
        } catch (e) {
            console.log(e)
        }
    }

    //Returns the list of recipes for this user
    static GetRecipesForUser(uid) {
        try {
            const url = this.apiUrl + '/recipe/allRecipes?authorid=' + uid 

            return fetch(url).then(r => r.json()).then(json => {
                let recipes = []

                //Converting JSON recipes to a recipe model
                for (const r of json) {
                    console.log(r)
                    let recipe = new Recipe(r.rid, r.uid, r.name, r.category, r.ingredients, r.instructions, r.origin, r.tags, r.imageURL, r.videoURL, r.preparationTime, r.likes)
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
                let recipe = new Recipe(r.rid, r.uid, r.name, r.category, r.ingr, r.instr, r.origin, r.tags, r.iurl, r.vurl, r.preparationTime, r.likes)
                return recipe
            })
        } catch (e) {
            console.log(e)
        }
    }

    //returns all liked recipes from this user
    static GetLikedRecipes(uid) {
        try {
            const url = this.apiUrl + '/likes/' + uid

            return fetch(url).then(r => r.json()).then(json => {
                let recipes = []
                //Converting JSON recipes to a recipe model
                for (const r of json) {
                    let recipe = new Recipe(r.rid, r.uid, r.name, r.category, r.ingr, r.instr, r.origin, r.tags, r.iurl, r.vurl, r.preparationTime, r.likes)
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
    static Register(name, surname, age, username, password, email){
        let body = JSON.stringify({
            name: name,
            surname: surname,
            age: age,
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
            uid: localStorage.getItem("uid"),
            name: name,
            category: category,
            ingredients: ingr,
            instructions: instr,
            origin: origin,
            tags: tags,
            imageURL: iurl,
            videoURL: vurl,
            preparationTime: preptime
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
            const url = this.apiUrl + '/profile/userid/' + uid
            return fetch(url).then(r => r.json())
        } catch (e) {
            console.log(e)
        }
    }

    //gets profile information based on its username
    static ProfileByUsername(username) {
        try {
            const encodedUsername = encodeURIComponent(username)
            const url = this.apiUrl + `/profile/username/` + encodedUsername
            return fetch(url)
        } catch (e) {
            console.log(e)
        }
    }

    //get UID from username
    static async GetUIDFromUsername(username) {
        try {
            const url = this.apiUrl + "/profile/username/" + username
            // Make a request to your backend to retrieve user information based on the username
            const response = await fetch(url);

            if (!response.ok) {
                // Handle error, throw an exception, or return a default value
                throw new Error(`Unable to fetch user information for username ${username}`);
            }

            const userData = await response.json();

            // Assuming your backend returns an object with a 'uid' property
            const uid = userData.uid;
            console.log(uid)

            return uid;
        } catch (error) {
            console.error('Error in GetUIDFromUsername:', error);
            // Handle the error, throw an exception, or return a default value
            return null;
        }
    }

    //get username from UID
    static async GetUsernameFromUID(uid) {
        try {
            // console.log(uid)
            const encodedUid = encodeURIComponent(uid)
            const url = this.apiUrl + '/profile/userid/' + encodedUid
            // console.log(url)
            // Make a request to your backend to retrieve user information based on the uid
            const response = await fetch(url);
            if (!response.ok) {
                // Handle error, throw an exception, or return a default value
                throw new Error(`Unable to fetch user information for UID ${uid}`);
            }

            const userData = await response.json();

            const username = userData.username;
            // console.log(uid)

            return username;
        } catch (error) {
            console.error('Error in GetUsernameFromUID:', error);
            return null;
        }
    }

    static async deleteProfile(uid) {
        try {
            const url = `${this.apiUrl}/profile/${uid}`;
            const response = await fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(`Unable to delete profile for UID ${uid}`);
            }

            return await response.json();
        } catch (error) {
            console.error('Error in deleteProfile:', error);
            return null;
        }
    }

    static async GetComments(rid) {
        try {
            const url = `${this.apiUrl}/comment/${rid}`;
            const response = await fetch(url);

            if (!response.ok) {
                throw new Error(`Failed to fetch comments for recipe ID ${rid}`);
            }

            const json = await response.json();
            const comments = json.map((commentData) => {
                return new Comment1(
                    commentData.userId,
                    commentData.recipeId,
                    commentData.timestamp,
                    commentData.text
                );
            });
            console.log(comments)
            return comments;
        } catch (error) {
            console.error('Error in GetComments:', error);
        }
    }

    static AddComment(rid, text){
        let body = JSON.stringify({
            userId: localStorage.getItem("uid"),
            recipeId: rid,
            text: text
        })
        console.log(body)
        try {
            const url = this.apiUrl + '/comment'
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

    //search recipes
    static searchRecipe(recipe) {
        try {
            const url = this.apiUrl + '/recipe/search/' + recipe
            console.log(url)
            return fetch(url)
        } catch (e) {
            console.log(e)
        }
    }

    //search profile
    static searchProfile(profile) {
        try {
            const url = this.apiUrl + '/profile/search/' + profile
            console.log(url)
            return fetch(url)
        } catch (e) {
            console.log(e)
        }
    }

    static LikeRecipe(rid, uid) {
        let body = JSON.stringify({
            rid: rid,
            uid: uid
        })
        console.log(body)
        try {
            const url = this.apiUrl + '/likes/like'
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

    static UnlikeRecipe(rid, uid) {
        let body = JSON.stringify({
            rid: rid,
            uid: uid
        })
        console.log(body)
        try {
            const url = this.apiUrl + '/likes?uid=' + uid + '&rid=' + rid
            return fetch(url, {
                method: 'DELETE',
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

    static async isRecipeLikedByUser(uid, recipeId) {
        try {
            const url = `${this.apiUrl}/recipe/allRecipes?loggedInUserId=${uid}`;
            console.log(url)
            const response = await fetch(url);

            if (!response.ok) {
                throw new Error(`Failed to fetch likes for user ID ${uid}`);
            }

            const likedRecipes = await response.json();

            // Find the recipe with the given recipeId
            const recipe = likedRecipes.find(recipe => recipe.recipeId === recipeId);

            if (recipe) {
                // console.log(recipe.likedByLoggedInUser)
                return recipe.likedByLoggedInUser;
            } else {
                // Recipe not found, you may want to handle this case accordingly
                console.warn(`Recipe with ID ${recipeId} not found.`);
                return false;
            }

        } catch (error) {
            console.error('Error in isRecipeLikedByUser:', error);
            return false; // Return false in case of an error
        }
    }

    /* static async isRecipeLikedByUser(rid, uid) {
        try {
            const url = `${this.apiUrl}/likes/${uid}`;
            console.log(url)
            const response = await fetch(url);

            if (!response.ok) {
                throw new Error(`Failed to fetch likes for user ID ${uid}`);
            }

            const likedRecipes = await response.json();
            console.log(likedRecipes)
            const isLiked = likedRecipes.some((like) => like.rid === rid);
            console.log(isLiked)
            return isLiked;
        } catch (error) {
            console.error('Error in isRecipeLikedByUser:', error);
            return false; // Return false in case of an error
        }
    } */

    static async getLikesForRecipe(rid) {
        try {
            const recipe = await this.GetRecipebyRid(rid);
            console.log(recipe.likes)
            return recipe.likes;
        } catch (e) {
            console.log(e);
        }
    }
}