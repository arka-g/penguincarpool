@extends ('template')

@section('content')
<h1>Hey man, you're logged in!</h1>
<h4>Your email is {{Auth::user()->email}}</h4>
<h5>Your first name is {{Auth::user()->first_name}}</h5>
<h5>Your last name is {{Auth::user()->last_name}}</h5>

<a href="{{ URL::to('logout') }}">Logout</a>
@stop