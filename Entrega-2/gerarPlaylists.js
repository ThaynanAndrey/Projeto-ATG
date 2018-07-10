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
    
    while(forest.length > 1) {
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
    let edges = [];
    let nodes = [];

    playlists.forEach((playlist, i) => {
        
        if(i < 2) {
            playlist.tracks.forEach((track, index) => {
                if(_.findIndex(nodes, (node) => node === track.track_name) === -1) {
                    nodes.push(track.track_name);
                }
                playlist.tracks.forEach((otherTrack, otherIndex) => {
                    if(index !== otherIndex) {
                        const newIndex = _.findIndex(edges, (branch) => 
                            (branch[FIRST_NODE_INDEX] === track.track_name && branch[SECOND_NODE_INDEX] === otherTrack.track_name)
                            || (branch[SECOND_NODE_INDEX] === track.track_name && branch[FIRST_NODE_INDEX] === otherTrack.track_name));

                        if(newIndex !== -1) {
                            edges[newIndex][WEIGHTED_EDGE_INDEX] = edges[newIndex][WEIGHTED_EDGE_INDEX] + 1;
                        } else {
                            edges.push([track.track_name, otherTrack.track_name, 1]);
                        }
                    }
                });    
            });
        }
    });
    return {edges: edges, nodes: nodes};
}

//breakTree(kruskal(nodes, edges));

loadPlaylists();
const graph = createMusicsGraph();
console.log(graph.edges);
console.log(kruskal(graph.nodes, graph.edges));