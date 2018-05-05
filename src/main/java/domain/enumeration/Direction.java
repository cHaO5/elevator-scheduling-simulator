package domain.enumeration;

public enum Direction {
    UP {
        @Override
        public Direction opposite() {
            return DOWN;
        }
    },
    DOWN {
        @Override
        public Direction opposite() {
            return UP;
        }
    },
    NONE {
        @Override
        public Direction opposite() {
            return NONE;
        }
    };

    public abstract Direction opposite();
}
