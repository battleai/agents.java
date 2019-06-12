import battleAI.grpc.ConnectFourAgentGrpc;
import battleAI.grpc.Interface.ConnectFour.Game;
import battleAI.grpc.Interface.ConnectFour.Move;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class RandomAgent extends ConnectFourAgentGrpc.ConnectFourAgentImplBase {

    @Override
    public void move(Game game, StreamObserver<Move> responseObserver) {
        Map<Integer, Integer> movesByColumn = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            movesByColumn.put(i, 0);
        }

        for (Move move : game.getMovesList()) {
            Integer count = movesByColumn.get(move.getMove());
            movesByColumn.put(move.getMove(), count + 1);
        }

        List<Integer> validMoves = movesByColumn.entrySet().stream()
                .filter(it -> it.getValue() < 6)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Collections.shuffle(validMoves);
        responseObserver.onNext(Move.newBuilder().setMove(validMoves.get(0)).build());
        responseObserver.onCompleted();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(5001).addService(new RandomAgent()).build().start();
        System.out.println("Listening on port 5001...");

        server.awaitTermination();
    }

}
