import RecipeList from "../components/RecipeList";

function Recipes(){
    return (
        <div>
        <h1 style={styles.title}>Kolekcija recepata</h1>
        <div style={styles.container}>
            <RecipeList />
        </div>
        </div>
    );
}


const styles = {
    container: {
        display: 'grid',
        gridTemplateColumns: '1fr 1fr',
        maxWidth: '800px',
        margin: 'auto',
        padding: '20px',
        border: '2px solid #ccc',
        borderRadius: '8px',
    },
    title: {
        textAlign: 'center',
        fontSize: '24px',
        marginBottom: '20px',
    },
};

export default Recipes;

