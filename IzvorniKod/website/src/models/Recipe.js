export default class Recipe{
    constructor(rid, uid, name, category, ingr, instr, origin, tags, url, likes) {
        this.rid = rid
        this.uid = uid
        this.name = name
        this.category = category
        this.ingredients = ingr
        this.instructions = instr
        this.origin = origin
        this.tags = tags
        this.url = url
        this.likes = likes
    }
}