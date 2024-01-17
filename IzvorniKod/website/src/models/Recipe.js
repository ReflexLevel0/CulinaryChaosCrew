export default class Recipe{
    constructor(rid, uid, name, category, ingr, instr, origin, tags, iurl, vurl, preptime, likes) {
        this.rid = rid
        this.uid = uid
        this.name = name
        this.category = category
        this.ingredients = ingr
        this.instructions = instr
        this.origin = origin
        this.tags = tags
        this.iurl = iurl
        this.vurl = vurl
        this.preptime = preptime
        this.likes = likes
    }
}