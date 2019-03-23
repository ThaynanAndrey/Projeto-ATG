const _ = require('underscore');
const fs = require('fs');
const stringify = require('csv-stringify');

let playlists = [];

function kruskal(nodes, edges) {
    const mst = [];
    let forest = _.map(nodes, (node) => [node]);
    const sortedEdges = _.sortBy(edges, (edge) => edge.weight);
    
    while(forest.length > 1 && sortedEdges.length > 0) {
        const edge = sortedEdges.pop();

        const n1 = edge.out,
            n2 = edge.in;

        const t1 = _.filter(forest, function(tree) {
            return _.include(tree, n1);
        });
            
        const t2 = _.filter(forest, function(tree) {
            return _.include(tree, n2);
        });

        if (!_.isEqual(t1, t2)) {
            forest = _.without(forest, t1[0], t2[0]);
            forest.push(_.union(t1[0], t2[0]));
            mst.push(edge);
        }
    }
    return mst;
}

function loadPlaylists() {
    let temp = fs.readFileSync('data.json');
    temp = JSON.parse(temp);
    playlists = temp.playlists;
};

function createMusicsRelations() {
    console.log("Criando relações entre as músicas..");
    let edges = {};

    playlists.forEach((playlist, i) => {
        console.log("Playlist de número: " + i);

        for(let i = 0; i < playlist.tracks.length; i++) {
            const track = playlist.tracks[i].track_name;

            for(let j = i+1; j < playlist.tracks.length; j++) {
                const otherTrack = playlist.tracks[j].track_name;
                let outEdgeIndex = _.findIndex(edges[track], edge => edge.in === otherTrack);
                let inEdgeIndex = _.findIndex(edges[otherTrack], edge => edge.in === track);
                let existsOutEdge = outEdgeIndex >= 0;
                let existsInEdge = inEdgeIndex >= 0;
                
                if (!existsOutEdge && !existsInEdge) {
                    edges[track] = edges[track] || [];
                    let edge = {out: track, in: otherTrack, weight: 1};
                    edges[track].push(edge);
                } else if (existsOutEdge && !existsInEdge) {
                    edges[track][outEdgeIndex].weight = edges[track][outEdgeIndex].weight + 1;
                } else if (!existsOutEdge && existsInEdge) {
                    edges[otherTrack][inEdgeIndex].weight = edges[otherTrack][inEdgeIndex].weight + 1;
                }
            }
        }
    });
    console.log("Relações criadas!");
    return edges;
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
            if(contador < 100) {
                novaPlaylist[i].tracks.splice(j, 1);
            }
        });
    });
    console.log("Feito..");
    return novaPlaylist.slice();
}

const writeFile = (nodes, fileName) => {
    console.log("Salvando " + fileName);
    stringify(nodes, function(err, resultado) {
      fs.writeFile(fileName, resultado, 'utf8', function(err) {
        if (err) {
          console.log('Ocorreu um erro na gravação do arquivo...');
        } else {''
          console.log(fileName + ' salvo!');
        }
      });
    });
};

const getNodes = (edges) => {
    let nodes = [];
    edges.forEach(e => {
        if (!_.contains(nodes, e.out)){
            nodes.push(e.out);
        }
        if (!_.contains(nodes, e.in)){
            nodes.push(e.in);
        }
    });
    return nodes;
};

(() => {
    loadPlaylists();
    playlists = filterLittlePopularMusics();
    const musicsRelations = createMusicsRelations();

    let edges = _.values(musicsRelations)
            .reduce((a, e) => [...a,...e])
            .filter(edge => edge.weight > 10);

    let nodes = getNodes(edges);
    let mstEdges = [];

    nodes = [["id", "label"]].concat(nodes.map(node => [node, node]));
    mstEdges = [["source", "target", "weight", "label"]]
            .concat(kruskal(edges)
                    .map(edge => [edge.out, edge.in, edge.weight, edge.weight]));
    edges = [["source", "target", "weight", "label"]]
            .concat(edges
                    .map(edge => [edge.out, edge.in, edge.weight, edge.weight]));
    
    writeFile(nodes, 'nodes.csv');
    writeFile(nodes, 'mstEdges.csv');
    writeFile(edges, 'edges.csv');
})();
