// Importa com <npm install underscore>
const _ = require('underscore');
const fs = require('fs');

let playlists = [];

const FIRST_NODE_INDEX = 0;
const SECOND_NODE_INDEX = 1;
const WEIGHTED_EDGE_INDEX = 2;

function kruskal(nodes, edges) {
    const mst = [];
    let forest = _.map(nodes, (node) => [node]);
    const sortedEdges = _.sortBy(edges, (edge) => edge[WEIGHTED_EDGE_INDEX]);
    
    while(forest.length > 1 && sortedEdges.length > 0) {
        const edge = sortedEdges.pop();

        const n1 = edge[FIRST_NODE_INDEX],
            n2 = edge[SECOND_NODE_INDEX];

        const t1 = _.filter(forest, function(tree) {
            return _.include(tree, n1);
        });
            
        const t2 = _.filter(forest, function(tree) {
            return _.include(tree, n2);
        });

        if (t1 != t2) {
            forest = _.without(forest, t1[0], t2[0]);
            forest.push(_.union(t1[0], t2[0]));
            mst.push(edge);
        }
    }
    return mst;
}

function breakTree(tree) {
    const minElement = _.min(tree, (branch) => branch[WEIGHTED_EDGE_INDEX])[WEIGHTED_EDGE_INDEX];
    const indexMinElement = _.findIndex(tree, (branch) => branch[WEIGHTED_EDGE_INDEX] === minElement);
    tree.splice(indexMinElement, 1);
    console.log("---------");
    console.log(tree);
}

function loadPlaylists() {
    let temp = fs.readFileSync('data.json');
    temp = JSON.parse(temp);
    playlists = temp.playlists;
};

function createMusicsGraph() {
    console.log("Criando grafo..");
    let edges = [];
    let nodes = [];

    playlists.forEach((playlist, h) => {
        
        if(h<1000) {

        for(let i = 0; i < playlist.tracks.length; i++) {
            const track = playlist.tracks[i];

            if(_.findIndex(nodes, (node) => node === track.track_name) === -1) {
                nodes.push(track.track_name);
            }

            for(let j = i+1; j < playlist.tracks.length; j++) {
                const otherTrack = playlist.tracks[j];
                const newIndex = _.findIndex(edges, (branch) => 
                    (branch[FIRST_NODE_INDEX] === track.track_name && branch[SECOND_NODE_INDEX] === otherTrack.track_name)
                    || (branch[SECOND_NODE_INDEX] === track.track_name && branch[FIRST_NODE_INDEX] === otherTrack.track_name));

                if(newIndex !== -1) {
                    edges[newIndex][WEIGHTED_EDGE_INDEX] = edges[newIndex][WEIGHTED_EDGE_INDEX] + 1;
                } else {
                    edges.push([track.track_name, otherTrack.track_name, 1]);
                }
            }
        }
        }
    });
    console.log("Grafo criado!");
    return {edges: edges, nodes: nodes};
}

function filterLittlePopularMusics() {
    console.log("Filtrando-se as músicas pouco escutadas..");
    let novaPlaylist = playlists.slice();
    playlists.forEach((playlist, i) => {
        
        playlist.tracks.forEach((track, j) => {
            
            let contador = 0;
            const trackName = track.track_name;
            playlists.forEach((playlist2) => {
                playlist2.tracks.forEach((track2) => {
                    if(track2.track_name === trackName) {
                        contador++;
                    }
                });
            });
            if(contador < 300) {
                novaPlaylist[i].tracks.splice(j, 1);
            }
        });
    });
    console.log("Feito..");
    return novaPlaylist.slice();
}

(() => {
    loadPlaylists();
    playlists = filterLittlePopularMusics();

    const graph = createMusicsGraph();

    console.log("Filtrando-se músicas com poucas relações");
    graph.edges = graph.edges.filter((edge) => edge[WEIGHTED_EDGE_INDEX] > 10);
    console.log("Filtrou-se as arestas..");

    console.log("Gerando árvore máxima geradora..");
    const maximumSpanningTree = kruskal(graph.nodes, graph.edges);

    console.log(maximumSpanningTree);
    //breakTree(kruskal(nodes, edges));
})();