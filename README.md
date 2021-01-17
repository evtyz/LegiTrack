## Inspiration

The whirlwind that was 2020 inspired our team to build something that would promote civic engagement. We wanted to connect real people with real issues, outside of the 24-hour news cycle. LegiTrack makes it possible for citizens to intimately engagement with legislation that they care about.

## What it does

LegiTrack provides users with a dashboard of recent legislation based on their location and subjects of interest. The user has the ability to learn more about each piece of legislation in-app. Additionally, the user can react to specific pieces of legislation in the form of providing a score or a comment. Reacts are databased, aggregated, and displayed to users in-app. The user can also share reactions to specific pieces of legislation on social media.

## How we built it

The data comes from the OpenStates API (https://openstates.org/), an open-source dataset that scrapes the web for legislation. We built our functionality and interface in android studio. Reactions (comments and scores) are databased using a Firebase real-time database.

## Challenges we ran into

We'd like to add a real-time notification component (e.g. users are notified in real time when new legislation that aligns with their profile is introduced), but that process involves adding somewhat complex features (e.g. background permissions, timers, etc.) that we couldn't quite implement in time. We also tried to leverage some neat Azure tools to streamline the search function, but found that learning curve to be a bit steep for 36 hours.

## Accomplishments that we're proud of

Wrangling the API data, building the comments feed, and enabling sharing are all features that we're super proud of!

## What we learned

That the civic process is often needlessly opaque, and there is room for disruption. We're full of good ideas on how to improve LegiTrack in the future.

## What's next for LegiTrack

In the future, we'll integrate more robust data sources to make sure our results are as complete as possible. A search function specific to our dataset will be honed and streamlined. Future iterations of LegiTrack will be temporally and geographically aware, and user data (and databased data) will be more secure. In addition, we'd like to implement ways for legislators to both receive and interact with their constituents' feedback on specific legislation. Our hope is to build a more robust conversation about legislation while it's happening, and to democratize access to real-time legislative information. 

# Video Demo

https://youtu.be/kQqWTyVe540
