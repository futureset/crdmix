Posting messages

Narrative: 
In order to share my thoughts and ideas with other users 
As a Mixchat user
I want to post messages to my personal timeline
					 
Scenario:  Alice publishes her messages to a personal timeline
Given Alice has posted message "I love the weather today"
When Alice reads her timeline
Then the last output contains "I love the weather today (Just now)"

Scenario:  A new user called Bob posts to his timeline
Given 3 minutes have passed
And Bob has posted message "Damn! We lost!"
And 60 seconds have passed
And Bob has posted message "Good game though"
And 60 seconds have passed
When Bob reads his timeline
Then the last output contains "Damn! We lost! (2 minutes ago)"
And the last output contains "Good game though (1 minute ago)"

Scenario:  Bob reads timeline of Alice
When Bob reads the timeline of Alice
Then the last output contains "I love the weather today (5 minutes ago)"

Scenario: Charlie follows Alice
Given Charlie has posted message "I'm in New York today! Anyone want to have a coffee?"
And Charlie follows Alice
And 2 seconds have passed 	
When wall of Charlie is read
Then the last output contains "Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)"
And the last output contains "Alice - I love the weather today (5 minutes ago)"

Scenario: Charlie follows Bob
Given Charlie follows Bob
And 13 seconds have passed
When wall of Charlie is read
Then the last output contains "Charlie - I'm in New York today! Anyone want to have a coffee? (15 seconds ago)"
And the last output contains "Bob - Good game though (1 minute ago)"
And the last output contains "Bob - Damn! We lost! (2 minutes ago)"
And the last output contains "Alice - I love the weather today (5 minutes ago)"

