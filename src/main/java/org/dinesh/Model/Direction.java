package org.dinesh.Model;

public enum Direction {
        UP_COMETH("up"),
        DOWN_COMETH("down"),
        RIGHT_COMETH("right"),
        LEFT_COMETH("left");

        private final String directionValue;

        Direction(String directionValue) {
                this.directionValue = directionValue;
        }

        public String getDirectionValue() {
                return directionValue;
        }

        public static Direction getByDirectionValue(String value) {
                for (Direction direction : Direction.values()) {
                        if (direction.name().equals(value)) {
                                return direction;
                        }
                }
                throw new IllegalArgumentException("Invalid direction value: " + value);
        }
}

