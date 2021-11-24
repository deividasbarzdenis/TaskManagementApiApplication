package lt.debarz.taskmanagementapi.user.model;


/**
 * This interface defines different Views for different models in application.
 */
public interface View {
    // Enclosing type to define User views
    public static interface UserView {
        // View for external users
        public static interface External {

        }
        // View for internal uses and services
        public static interface Internal extends External{


            // View for User GET call request body
            public static interface Get {
            }

            /**
             * View to define deserialization of request body for POST call. any fields
             * other than defined by this view, will be just ignored.
             */
            public static interface Post {
            }

            // View for User PUT call request body
            public static interface PUT {
            }

            // View for User PATCH call request body
            public static interface Patch {
            }

            // View for User DELETE call request body
            public static interface Delete {
            }
        }
    }
}
